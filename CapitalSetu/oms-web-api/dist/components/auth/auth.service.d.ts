import { ConfigService } from '@nestjs/config';
import { OrganisationsService } from '../organisations/organisations.service';
import { UsersService } from '../users/users.service';
export declare class AuthService {
    private readonly usersService;
    private readonly organisationsService;
    private readonly configService;
    constructor(usersService: UsersService, organisationsService: OrganisationsService, configService: ConfigService);
    signup(mobileNumber: string, password: string, businessType: string): Promise<{
        status: string;
        message: string;
    }>;
    login(mobileNumber: string, password: string): Promise<{
        accessToken: any;
        status: string;
        message: string;
    }>;
    private hashPassword;
    private comparePasswords;
}
