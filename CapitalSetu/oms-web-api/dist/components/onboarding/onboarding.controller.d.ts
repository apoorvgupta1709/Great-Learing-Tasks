import { BasicGstOnboardingDto } from './dtos/basic-gst-onboarding.dto';
import { BasicNoGstOnboardingDto } from './dtos/basic-no-gst-onboarding.dto';
import { OnboardingService } from './onboarding.service';
export declare class OnboardingController {
    private readonly onboardingService;
    constructor(onboardingService: OnboardingService);
    testRoute(): string;
    verifyGst(gstin: string): Promise<{
        gstin: string;
        status: string;
        businessEntityName: any;
        businessEntityType: any;
        businessOwners: any;
        businessAddress: any;
        ownersPan: string;
        message?: undefined;
    } | {
        gstin: string;
        status: string;
        businessEntityName: any;
        businessEntityType: any;
        businessOwners: any;
        businessAddress: any;
        ownersPan?: undefined;
        message?: undefined;
    } | {
        status: string;
        message: string;
        gstin?: undefined;
        businessEntityName?: undefined;
        businessEntityType?: undefined;
        businessOwners?: undefined;
        businessAddress?: undefined;
        ownersPan?: undefined;
    }>;
    verifyPan(pan: string): Promise<{
        status: string;
        pan: string;
        panHolderName: any;
        linkedGstin: any;
        message?: undefined;
    } | {
        status: string;
        message: string;
        pan?: undefined;
        panHolderName?: undefined;
        linkedGstin?: undefined;
    }>;
    basicGstOnboarding(basicGstOnboardingDto: BasicGstOnboardingDto, userId: string, organisationId: string): Promise<{
        status: string;
        message: string;
    }>;
    basicNoGstOnboarding(basicNoGstOnboardingDto: BasicNoGstOnboardingDto, userId: string, organisationId: string): Promise<{
        status: string;
        message: string;
    }>;
    undoBasicGstOnboarding(userId: string, organisationId: string): Promise<{
        status: string;
        message: string;
    }>;
    undoBasicNoGstOnboarding(userId: string, organisationId: string): Promise<{
        status: string;
        message: string;
    }>;
}
