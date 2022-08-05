import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type UserDocument = User & Document;
export declare class User {
    name: string;
    email: string;
    password: string;
    organisation_id: Organisation;
    role: string;
    mobile_number: string;
    otp: string;
    otp_created_at: string;
}
export declare const UserSchema: mongoose.Schema<Document<User, any, any>, mongoose.Model<any, any, any>, undefined, any>;
