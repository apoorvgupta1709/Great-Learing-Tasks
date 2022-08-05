import { HttpService } from '@nestjs/axios';
import { ConfigService } from '@nestjs/config';
export declare class KarzaService {
    private readonly httpService;
    private readonly configService;
    static readonly API_SUCCESS = 101;
    static readonly API_AUTH_FAIL = 102;
    static readonly API_SEARCH_FAIL = 103;
    constructor(httpService: HttpService, configService: ConfigService);
    verifyGst(gstin: string): Promise<any>;
    verifyPan(pan: any): Promise<any>;
    searchGstForPan(pan: any): Promise<any>;
    verifyPanDetails(pan: string, name: string, dob: string): Promise<any>;
}
