import { Module } from '@nestjs/common';
import { OrganisationsModule } from '../organisations/organisations.module';
import { UsersModule } from '../users/users.module';
import { AuthController } from './auth.controller';
import { AuthService } from './auth.service';

@Module({
  imports: [UsersModule, OrganisationsModule],
  controllers: [AuthController],
  providers: [AuthService],
})
export class AuthModule {}
