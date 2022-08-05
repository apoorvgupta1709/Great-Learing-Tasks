import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type ProductCodeDocument = ProductCode & Document;
export declare class ProductCode {
    organisation_id: Organisation;
    code: string;
}
export declare const ProductCodeSchema: mongoose.Schema<Document<ProductCode, any, any>, mongoose.Model<any, any, any>, undefined, any>;
