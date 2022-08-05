import { AuthService } from './auth.service';
export declare class AuthController {
    private readonly authService;
    constructor(authService: AuthService);
    testRoute(): string;
    signup(businessType: string, mobileNumber: string, password: string): Promise<{
        status: string;
        message: string;
    }>;
    login(mobileNumber: string, password: string): Promise<{
        accessToken: any;
        status: string;
        message: string;
    }>;
}
