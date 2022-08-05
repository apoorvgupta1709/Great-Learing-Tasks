import { Module } from '@nestjs/common';
import { MongooseModule } from '@nestjs/mongoose';
import { AddressSchema } from './addresses.schema';
import { AddressesService } from './addresses.service';

@Module({
  imports: [
    MongooseModule.forFeature([
      {
        name: 'Address',
        schema: AddressSchema,
      },
    ]),
  ],
  providers: [AddressesService],
  exports: [AddressesService],
})
export class AddressesModule {}
