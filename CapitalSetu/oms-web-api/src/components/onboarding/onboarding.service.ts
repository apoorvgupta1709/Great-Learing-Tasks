import {
  BadRequestException,
  ConflictException,
  Injectable,
} from '@nestjs/common';
import { AddressesService } from '../addresses/addresses.service';
import { KarzaService } from '../karza/karza.service';
import { OrganisationsService } from '../organisations/organisations.service';
import { UsersService } from '../users/users.service';
import { BasicGstOnboardingDto } from './dtos/basic-gst-onboarding.dto';
import { BasicNoGstOnboardingDto } from './dtos/basic-no-gst-onboarding.dto';
import * as mongoose from 'mongoose';

@Injectable()
export class OnboardingService {
  private readonly businessEntityTypes = {
    PROPRIETORSHIP: 'Proprietorship',
    PARTNERSHIP: 'Partnership',
    PVT_LTD: 'Private Limited Company',
    PUB_LTD: 'Public Limited Company',
  };

  private readonly ATOMIC_MODIFIED_COUNT = 1;

  constructor(
    private readonly karzaService: KarzaService,
    private readonly usersService: UsersService,
    private readonly organisationsService: OrganisationsService,
    private readonly addressesService: AddressesService,
  ) {}

  async verifyGst(gstin: string) {
    // Verify whether GST is valid
    const { result, statusCode } = await this.karzaService.verifyGst(gstin);

    if (
      statusCode === KarzaService.API_SUCCESS &&
      result.sts === 'Active' &&
      result.gstin === gstin
    ) {
      // Valid GSTIN
      if (result.ctb === this.businessEntityTypes.PROPRIETORSHIP) {
        return {
          gstin,
          status: 'VALID_GSTIN',
          businessEntityName: result.tradeNam,
          businessEntityType: result.ctb,
          businessOwners: result.mbr,
          businessAddress: result.pradr.adr,
          ownersPan: this.getPanFromGstin(result.gstin),
        };
      } else if (
        [
          this.businessEntityTypes.PARTNERSHIP,
          this.businessEntityTypes.PVT_LTD,
          this.businessEntityTypes.PUB_LTD,
        ].includes(result.ctb)
      ) {
        return {
          gstin,
          status: 'VALID_GSTIN',
          businessEntityName: result.tradeNam,
          businessEntityType: result.ctb,
          businessOwners: result.mbr,
          businessAddress: result.pradr.adr,
        };
      }
    } else if (statusCode === KarzaService.API_AUTH_FAIL) {
      // Invalid GSTIN
      return {
        status: 'INVALID_GSTIN',
        message: 'The Gstin provided is invalid',
      };
    }
  }

  async verifyPan(pan: string) {
    // Verify whether PAN is valid
    const verifyResponse = await this.karzaService.verifyPan(pan);
    const verifyResult = verifyResponse.result;
    const verifyStatusCode = parseInt(verifyResponse['status-code']);

    if (verifyStatusCode === KarzaService.API_SUCCESS) {
      // Valid PAN
      // Now Search GSTINs linked with given PAN
      const searchResponse = await this.karzaService.searchGstForPan(pan);
      const searchResult = searchResponse.result;
      const searchStatusCode = searchResponse.statusCode;

      if (searchStatusCode === KarzaService.API_SUCCESS) {
        // One or more GSTINs are linked with PAN
        return {
          status: 'VALID_PAN',
          pan,
          panHolderName: verifyResult.name,
          linkedGstin: searchResult.map((res) => res.gstinId),
        };
      } else if (searchStatusCode === KarzaService.API_SEARCH_FAIL) {
        // No GSTINs linked with PAN
        return {
          status: 'VALID_PAN',
          pan,
          panHolderName: verifyResult.name,
          linkedGstin: [],
        };
      }
    } else if (verifyStatusCode === KarzaService.API_AUTH_FAIL) {
      // Invalid PAN
      return {
        status: 'INVALID_PAN',
        message: 'The pan provided is invalid',
      };
    }
  }

  async basicGstOnboarding(
    basicGstOnboardingDto: BasicGstOnboardingDto,
    userId: string,
    organisationId: string,
  ) {
    const {
      userPan,
      userName,
      userDOB,
      userEmail,
      organisationGstin,
      organisationEntityType,
    } = basicGstOnboardingDto;
    // Verify PAN Name and DOB
    const response = await this.karzaService.verifyPanDetails(
      userPan,
      userName,
      userDOB,
    );
    const result = response.result;
    const statusCode = parseInt(response['status-code']);

    if (
      statusCode === KarzaService.API_SUCCESS &&
      result.nameMatch &&
      result.dobMatch
    ) {
      // Save user and organisation details
      const userUpdateResult = await this.usersService.updateOneById(userId, {
        name: userName,
        email: userEmail,
        dob: userDOB,
      });
      const organisationUpdateResult =
        await this.organisationsService.updateOneById(organisationId, {
          gstin: organisationGstin,
          pan: userPan,
          organisationEntityType: organisationEntityType,
        });
      console.log({ userUpdateResult, organisationUpdateResult });
      if (
        userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
        organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT
      ) {
        return {
          status: 'BASIC_GST_ONBOARDING_SUCCESS',
          message: 'The basic gst onboarding has been successful',
        };
      } else {
        throw new ConflictException('Onboarding details cannot be updated');
      }
    } else if (!result.nameMatch || !result.dobMatch) {
      // Either or both Name and DOB don't match with PAN details
      throw new BadRequestException('Pan details do not match.');
    }
  }

  async basicNoGstOnboarding(
    basicNoGstOnboardingDto: BasicNoGstOnboardingDto,
    userId: string,
    organisationId: string,
  ) {
    const {
      userPan,
      userName,
      userDOB,
      userEmail,
      organisationEntityType,
      organisationName,
      organisationAddress,
    } = basicNoGstOnboardingDto;

    // Verify PAN Name and DOB
    const response = await this.karzaService.verifyPanDetails(
      userPan,
      userName,
      userDOB,
    );
    const result = response.result;
    const statusCode = parseInt(response['status-code']);

    if (
      statusCode === KarzaService.API_SUCCESS &&
      result.nameMatch &&
      result.dobMatch
    ) {
      // Save user, organisation and address details
      const userUpdateResult = await this.usersService.updateOneById(userId, {
        name: userName,
        email: userEmail,
        dob: userDOB,
      });
      const organisationUpdateResult =
        await this.organisationsService.updateOneById(organisationId, {
          pan: userPan,
          organisationEntityType: organisationEntityType,
          name: organisationName,
        });
      const addressCreateResult = await this.addressesService.create({
        organisationId: mongoose.Types.ObjectId(organisationId),
        ...organisationAddress,
      });
      console.log({
        userUpdateResult,
        organisationUpdateResult,
        addressCreateResult,
      });
      if (
        userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
        organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
        addressCreateResult
      ) {
        return {
          status: 'BASIC_NO_GST_ONBOARDING_SUCCESS',
          message: 'The basic no-gst onboarding has been successful',
        };
      } else {
        throw new ConflictException('Onboarding details cannot be updated');
      }
    } else if (!result.nameMatch || !result.dobMatch) {
      // Either or both Name and DOB don't match with PAN details
      throw new BadRequestException('Pan details do not match.');
    }
  }

  async undoBasicGstOnboarding(userId: string, organisationId: string) {
    const userUpdateResult = await this.usersService.updateOneById(userId, {
      name: undefined,
      email: undefined,
      dob: undefined,
    });
    const organisationUpdateResult =
      await this.organisationsService.updateOneById(organisationId, {
        gstin: undefined,
        pan: undefined,
        organisationEntityType: undefined,
      });
    console.log({ userUpdateResult, organisationUpdateResult });
    if (
      userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
      organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT
    ) {
      return {
        status: 'RESET_BASIC_GST_ONBOARDING_SUCCESS',
        message: 'The basic gst onboarding has been undone',
      };
    }

    throw new ConflictException('Modification failed, already in Reset State');
  }

  async undoBasicNoGstOnboarding(userId: string, organisationId: string) {
    const userUpdateResult = await this.usersService.updateOneById(userId, {
      name: undefined,
      email: undefined,
      dob: undefined,
    });
    const organisationUpdateResult =
      await this.organisationsService.updateOneById(organisationId, {
        pan: undefined,
        organisationEntityType: undefined,
        name: undefined,
      });
    const deleteAddressResult = await this.addressesService.deleteOne({
      organisationId: mongoose.Types.ObjectId(organisationId),
    });
    console.log({
      userUpdateResult,
      organisationUpdateResult,
      deleteAddressResult,
    });
    if (
      userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
      organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT
    ) {
      return {
        status: 'RESET_BASIC_NO_GST_ONBOARDING_SUCCESS',
        message: 'The basic no-gst onboarding has been undone',
      };
    }

    throw new ConflictException('Modification failed, already in Reset State');
  }

  private getPanFromGstin(gstin: string): string {
    // This is valid only for GSTINs belonging to Propreitorships
    const START_INDEX = 2;
    const PAN_LENGTH = 10;
    return gstin.substring(START_INDEX, START_INDEX + PAN_LENGTH);
  }

  private toTitleCase(str) {
    return str.replace(/\w\S*/g, (txt) => {
      return txt.charAt(0).toUpperCase() + txt.substring(1).toLowerCase();
    });
  }
}
