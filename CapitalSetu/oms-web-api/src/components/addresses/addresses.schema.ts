import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';
import * as mongoose from 'mongoose';

export type AddressDocument = Address & Document;

@Schema()
export class Address {
  @Prop({
    required: true,
    ref: 'Organisation',
  })
  organisationId: mongoose.Types.ObjectId;

  @Prop()
  line1: string;

  @Prop()
  line2: string;

  @Prop()
  landmark: string;

  @Prop()
  city: string;

  @Prop()
  town: string;

  @Prop()
  pinCode: string;

  @Prop({ default: true })
  isDefault: boolean;
}

export const AddressSchema = SchemaFactory.createForClass(Address);
