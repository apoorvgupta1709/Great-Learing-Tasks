import {
  Body,
  Controller,
  Get,
  HttpCode,
  HttpStatus,
  Post,
} from '@nestjs/common';
import { AuthService } from './auth.service';

@Controller('auth')
export class AuthController {
  constructor(private readonly authService: AuthService) {}

  @Get('test')
  testRoute() {
    return 'auth: test route active';
  }

  @Post('signup')
  @HttpCode(HttpStatus.CREATED)
  async signup(
    @Body('businessType') businessType: string,
    @Body('mobileNumber') mobileNumber: string,
    @Body('password') password: string,
  ) {
    return await this.authService.signup(mobileNumber, password, businessType);
  }

  @Post('login')
  @HttpCode(HttpStatus.OK)
  async login(
    @Body('mobileNumber') mobileNumber: string,
    @Body('password') password: string,
  ) {
    return await this.authService.login(mobileNumber, password);
  }
}
