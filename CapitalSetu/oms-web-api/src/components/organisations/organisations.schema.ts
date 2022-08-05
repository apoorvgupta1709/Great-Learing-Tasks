import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';
import * as mongoose from 'mongoose';

export type OrganisationDocument = Organisation & Document;

@Schema()
export class Organisation {
  @Prop({ required: true, enum: ['VENDOR', 'CORPORATE', 'DEALER', 'RETAILER'] })
  organisationType: string;

  @Prop({ enum: ['PROPRIETORSHIP', 'PARTNERSHIP', 'PVT_LTD', 'PUB_LTD'] })
  organisationEntityType: string;

  @Prop()
  name: string;

  @Prop()
  gstin: string;

  @Prop()
  pan: string;

  @Prop({ required: true, ref: 'User' })
  ownerId: mongoose.Types.ObjectId;

  @Prop()
  verified: boolean;
}

export const OrganisationSchema = SchemaFactory.createForClass(Organisation);
