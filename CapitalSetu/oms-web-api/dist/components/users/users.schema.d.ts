import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
export declare type UserDocument = User & Document;
export declare class User {
    name: string;
    email: string;
    dob: string;
    password: string;
    organisationId: mongoose.Types.ObjectId;
    role: string;
    mobileNumber: string;
    otp: string;
    otpCreatedAt: string;
}
export declare const UserSchema: mongoose.Schema<Document<User, any, any>, mongoose.Model<any, any, any>, undefined, any>;
