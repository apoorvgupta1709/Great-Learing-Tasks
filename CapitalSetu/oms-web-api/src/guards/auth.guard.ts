import { Injectable, CanActivate, ExecutionContext } from '@nestjs/common';
import * as jwt from 'jsonwebtoken';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class AuthGuard implements CanActivate {
  constructor(private readonly configService: ConfigService) {}

  canActivate(context: ExecutionContext): boolean {
    const request = context.switchToHttp().getRequest();
    const authHeader = request.headers['authorization'];
    const accessToken = authHeader && authHeader.split(' ')[1];

    if (accessToken) {
      try {
        const user = jwt.verify(
          accessToken,
          this.configService.get('ACCESS_TOKEN_SECRET'),
        );

        // Adding the user to the request object
        request.user = user;

        return true;
      } catch (error) {
        console.error(error);
      }
    }

    return false;
  }
}
