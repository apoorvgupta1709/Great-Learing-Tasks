"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.OnboardingService = void 0;
const common_1 = require("@nestjs/common");
const addresses_service_1 = require("../addresses/addresses.service");
const karza_service_1 = require("../karza/karza.service");
const organisations_service_1 = require("../organisations/organisations.service");
const users_service_1 = require("../users/users.service");
const mongoose = require("mongoose");
let OnboardingService = class OnboardingService {
    constructor(karzaService, usersService, organisationsService, addressesService) {
        this.karzaService = karzaService;
        this.usersService = usersService;
        this.organisationsService = organisationsService;
        this.addressesService = addressesService;
        this.businessEntityTypes = {
            PROPRIETORSHIP: 'Proprietorship',
            PARTNERSHIP: 'Partnership',
            PVT_LTD: 'Private Limited Company',
            PUB_LTD: 'Public Limited Company',
        };
        this.ATOMIC_MODIFIED_COUNT = 1;
    }
    async verifyGst(gstin) {
        const { result, statusCode } = await this.karzaService.verifyGst(gstin);
        if (statusCode === karza_service_1.KarzaService.API_SUCCESS &&
            result.sts === 'Active' &&
            result.gstin === gstin) {
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
            }
            else if ([
                this.businessEntityTypes.PARTNERSHIP,
                this.businessEntityTypes.PVT_LTD,
                this.businessEntityTypes.PUB_LTD,
            ].includes(result.ctb)) {
                return {
                    gstin,
                    status: 'VALID_GSTIN',
                    businessEntityName: result.tradeNam,
                    businessEntityType: result.ctb,
                    businessOwners: result.mbr,
                    businessAddress: result.pradr.adr,
                };
            }
        }
        else if (statusCode === karza_service_1.KarzaService.API_AUTH_FAIL) {
            return {
                status: 'INVALID_GSTIN',
                message: 'The Gstin provided is invalid',
            };
        }
    }
    async verifyPan(pan) {
        const verifyResponse = await this.karzaService.verifyPan(pan);
        const verifyResult = verifyResponse.result;
        const verifyStatusCode = parseInt(verifyResponse['status-code']);
        if (verifyStatusCode === karza_service_1.KarzaService.API_SUCCESS) {
            const searchResponse = await this.karzaService.searchGstForPan(pan);
            const searchResult = searchResponse.result;
            const searchStatusCode = searchResponse.statusCode;
            if (searchStatusCode === karza_service_1.KarzaService.API_SUCCESS) {
                return {
                    status: 'VALID_PAN',
                    pan,
                    panHolderName: verifyResult.name,
                    linkedGstin: searchResult.map((res) => res.gstinId),
                };
            }
            else if (searchStatusCode === karza_service_1.KarzaService.API_SEARCH_FAIL) {
                return {
                    status: 'VALID_PAN',
                    pan,
                    panHolderName: verifyResult.name,
                    linkedGstin: [],
                };
            }
        }
        else if (verifyStatusCode === karza_service_1.KarzaService.API_AUTH_FAIL) {
            return {
                status: 'INVALID_PAN',
                message: 'The pan provided is invalid',
            };
        }
    }
    async basicGstOnboarding(basicGstOnboardingDto, userId, organisationId) {
        const { userPan, userName, userDOB, userEmail, organisationGstin, organisationEntityType, } = basicGstOnboardingDto;
        const response = await this.karzaService.verifyPanDetails(userPan, userName, userDOB);
        const result = response.result;
        const statusCode = parseInt(response['status-code']);
        if (statusCode === karza_service_1.KarzaService.API_SUCCESS &&
            result.nameMatch &&
            result.dobMatch) {
            const userUpdateResult = await this.usersService.updateOneById(userId, {
                name: userName,
                email: userEmail,
                dob: userDOB,
            });
            const organisationUpdateResult = await this.organisationsService.updateOneById(organisationId, {
                gstin: organisationGstin,
                pan: userPan,
                organisationEntityType: organisationEntityType,
            });
            console.log({ userUpdateResult, organisationUpdateResult });
            if (userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
                organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT) {
                return {
                    status: 'BASIC_GST_ONBOARDING_SUCCESS',
                    message: 'The basic gst onboarding has been successful',
                };
            }
            else {
                throw new common_1.ConflictException('Onboarding details cannot be updated');
            }
        }
        else if (!result.nameMatch || !result.dobMatch) {
            throw new common_1.BadRequestException('Pan details do not match.');
        }
    }
    async basicNoGstOnboarding(basicNoGstOnboardingDto, userId, organisationId) {
        const { userPan, userName, userDOB, userEmail, organisationEntityType, organisationName, organisationAddress, } = basicNoGstOnboardingDto;
        const response = await this.karzaService.verifyPanDetails(userPan, userName, userDOB);
        const result = response.result;
        const statusCode = parseInt(response['status-code']);
        if (statusCode === karza_service_1.KarzaService.API_SUCCESS &&
            result.nameMatch &&
            result.dobMatch) {
            const userUpdateResult = await this.usersService.updateOneById(userId, {
                name: userName,
                email: userEmail,
                dob: userDOB,
            });
            const organisationUpdateResult = await this.organisationsService.updateOneById(organisationId, {
                pan: userPan,
                organisationEntityType: organisationEntityType,
                name: organisationName,
            });
            const addressCreateResult = await this.addressesService.create(Object.assign({ organisationId: mongoose.Types.ObjectId(organisationId) }, organisationAddress));
            console.log({
                userUpdateResult,
                organisationUpdateResult,
                addressCreateResult,
            });
            if (userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
                organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
                addressCreateResult) {
                return {
                    status: 'BASIC_NO_GST_ONBOARDING_SUCCESS',
                    message: 'The basic no-gst onboarding has been successful',
                };
            }
            else {
                throw new common_1.ConflictException('Onboarding details cannot be updated');
            }
        }
        else if (!result.nameMatch || !result.dobMatch) {
            throw new common_1.BadRequestException('Pan details do not match.');
        }
    }
    async undoBasicGstOnboarding(userId, organisationId) {
        const userUpdateResult = await this.usersService.updateOneById(userId, {
            name: undefined,
            email: undefined,
            dob: undefined,
        });
        const organisationUpdateResult = await this.organisationsService.updateOneById(organisationId, {
            gstin: undefined,
            pan: undefined,
            organisationEntityType: undefined,
        });
        console.log({ userUpdateResult, organisationUpdateResult });
        if (userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
            organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT) {
            return {
                status: 'RESET_BASIC_GST_ONBOARDING_SUCCESS',
                message: 'The basic gst onboarding has been undone',
            };
        }
        throw new common_1.ConflictException('Modification failed, already in Reset State');
    }
    async undoBasicNoGstOnboarding(userId, organisationId) {
        const userUpdateResult = await this.usersService.updateOneById(userId, {
            name: undefined,
            email: undefined,
            dob: undefined,
        });
        const organisationUpdateResult = await this.organisationsService.updateOneById(organisationId, {
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
        if (userUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT &&
            organisationUpdateResult.nModified === this.ATOMIC_MODIFIED_COUNT) {
            return {
                status: 'RESET_BASIC_NO_GST_ONBOARDING_SUCCESS',
                message: 'The basic no-gst onboarding has been undone',
            };
        }
        throw new common_1.ConflictException('Modification failed, already in Reset State');
    }
    getPanFromGstin(gstin) {
        const START_INDEX = 2;
        const PAN_LENGTH = 10;
        return gstin.substring(START_INDEX, START_INDEX + PAN_LENGTH);
    }
    toTitleCase(str) {
        return str.replace(/\w\S*/g, (txt) => {
            return txt.charAt(0).toUpperCase() + txt.substring(1).toLowerCase();
        });
    }
};
OnboardingService = __decorate([
    common_1.Injectable(),
    __metadata("design:paramtypes", [karza_service_1.KarzaService,
        users_service_1.UsersService,
        organisations_service_1.OrganisationsService,
        addresses_service_1.AddressesService])
], OnboardingService);
exports.OnboardingService = OnboardingService;
//# sourceMappingURL=onboarding.service.js.map