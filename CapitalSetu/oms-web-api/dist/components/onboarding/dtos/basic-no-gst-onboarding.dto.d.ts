import { Address } from 'src/components/addresses/addresses.schema';
export declare class BasicNoGstOnboardingDto {
    userPan: string;
    userName: string;
    userDOB: string;
    userEmail: string;
    organisationEntityType: string;
    organisationName: string;
    organisationAddress: Partial<Address>;
}
