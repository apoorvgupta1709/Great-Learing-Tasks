import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
export declare type OrganisationDocument = Organisation & Document;
export declare class Organisation {
    organisationType: string;
    organisationEntityType: string;
    name: string;
    gstin: string;
    pan: string;
    ownerId: mongoose.Types.ObjectId;
    verified: boolean;
}
export declare const OrganisationSchema: mongoose.Schema<Document<Organisation, any, any>, mongoose.Model<any, any, any>, undefined, any>;
