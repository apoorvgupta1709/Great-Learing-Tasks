import {
  Body,
  Controller,
  Get,
  HttpCode,
  HttpStatus,
  Post,
  UseGuards,
} from '@nestjs/common';
import { AuthUser } from 'src/decorators/auth-user.decorator';
import { AuthGuard } from 'src/guards/auth.guard';
import { BasicGstOnboardingDto } from './dtos/basic-gst-onboarding.dto';
import { BasicNoGstOnboardingDto } from './dtos/basic-no-gst-onboarding.dto';
import { OnboardingService } from './onboarding.service';

@UseGuards(AuthGuard)
@Controller('onboarding')
export class OnboardingController {
  constructor(private readonly onboardingService: OnboardingService) {}

  @Get('test')
  testRoute() {
    return 'onboarding: test route active';
  }

  @Post('verify-gstin')
  @HttpCode(HttpStatus.OK)
  async verifyGst(@Body('gstin') gstin: string) {
    return await this.onboardingService.verifyGst(gstin);
  }

  @Post('verify-pan')
  @HttpCode(HttpStatus.OK)
  async verifyPan(@Body('pan') pan: string) {
    return await this.onboardingService.verifyPan(pan);
  }

  @Post('basic-gst')
  @HttpCode(HttpStatus.CREATED)
  async basicGstOnboarding(
    @Body() basicGstOnboardingDto: BasicGstOnboardingDto,
    @AuthUser('_id') userId: string,
    @AuthUser('organisationId') organisationId: string,
  ) {
    return await this.onboardingService.basicGstOnboarding(
      basicGstOnboardingDto,
      userId,
      organisationId,
    );
  }

  @Post('basic-no-gst')
  @HttpCode(HttpStatus.CREATED)
  async basicNoGstOnboarding(
    @Body() basicNoGstOnboardingDto: BasicNoGstOnboardingDto,
    @AuthUser('_id') userId: string,
    @AuthUser('organisationId') organisationId: string,
  ) {
    return await this.onboardingService.basicNoGstOnboarding(
      basicNoGstOnboardingDto,
      userId,
      organisationId,
    );
  }

  // TODO: This API is only for dev testing
  @Post('undo-basic-gst')
  @HttpCode(HttpStatus.OK)
  async undoBasicGstOnboarding(
    @AuthUser('_id') userId: string,
    @AuthUser('organisationId') organisationId: string,
  ) {
    return await this.onboardingService.undoBasicGstOnboarding(
      userId,
      organisationId,
    );
  }

  // TODO: This API is only for dev testing
  @Post('undo-basic-no-gst')
  @HttpCode(HttpStatus.OK)
  async undoBasicNoGstOnboarding(
    @AuthUser('_id') userId: string,
    @AuthUser('organisationId') organisationId: string,
  ) {
    return await this.onboardingService.undoBasicNoGstOnboarding(
      userId,
      organisationId,
    );
  }
}
