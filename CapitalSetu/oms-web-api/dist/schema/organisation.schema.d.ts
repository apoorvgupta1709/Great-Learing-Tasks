import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { User } from './user.schema';
export declare type OrganisationDocument = Organisation & Document;
export declare class Organisation {
    organisation_type: string;
    name: string;
    gst: string;
    pan: string;
    owner_id: User;
    verified: boolean;
}
export declare const OrganisationSchema: mongoose.Schema<Document<Organisation, any, any>, mongoose.Model<any, any, any>, undefined, any>;
