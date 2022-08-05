import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { OrganisationSchema } from './organisations.schema';
import { OrganisationsService } from './organisations.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      {
        name: 'Organisation',
        schema: OrganisationSchema,
      },
    ]),
  ],
  providers: [OrganisationsService],
  exports: [OrganisationsService],
})
export class OrganisationsModule {}
