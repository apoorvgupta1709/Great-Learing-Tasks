import { Module } from '@nestjs/common';
import { AddressesModule } from '../addresses/addresses.module';
import { KarzaModule } from '../karza/karza.module';
import { OrganisationsModule } from '../organisations/organisations.module';
import { UsersModule } from '../users/users.module';
import { OnboardingController } from './onboarding.controller';
import { OnboardingService } from './onboarding.service';

@Module({
  imports: [KarzaModule, UsersModule, OrganisationsModule, AddressesModule],
  controllers: [OnboardingController],
  providers: [OnboardingService],
})
export class OnboardingModule {}
