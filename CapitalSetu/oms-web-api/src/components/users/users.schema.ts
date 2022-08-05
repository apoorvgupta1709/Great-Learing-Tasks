import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';
import * as mongoose from 'mongoose';

export type UserDocument = User & Document;

@Schema()
export class User {
  @Prop()
  name: string;

  @Prop()
  email: string;

  @Prop()
  dob: string;

  @Prop()
  password: string;

  @Prop({
    required: true,
    ref: 'Organisation',
  })
  organisationId: mongoose.Types.ObjectId;

  @Prop({
    required: true,
    enum: ['ADMIN', 'MODERATOR', 'GUEST'],
    default: 'ADMIN',
  })
  role: string;

  @Prop({ required: true })
  mobileNumber: string;

  @Prop()
  otp: string;

  @Prop()
  otpCreatedAt: string;
}

export const UserSchema = SchemaFactory.createForClass(User);
