import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
export declare type AddressDocument = Address & Document;
export declare class Address {
    organisationId: mongoose.Types.ObjectId;
    line1: string;
    line2: string;
    landmark: string;
    city: string;
    town: string;
    pinCode: string;
    isDefault: boolean;
}
export declare const AddressSchema: mongoose.Schema<Document<Address, any, any>, mongoose.Model<any, any, any>, undefined, any>;
