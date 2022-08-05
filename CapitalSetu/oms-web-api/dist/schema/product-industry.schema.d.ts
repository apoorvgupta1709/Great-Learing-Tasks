import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type ProductIndustryDocument = ProductIndustry & Document;
export declare class ProductIndustry {
    organisation_id: Organisation;
    industry: string;
}
export declare const ProductIndustrySchema: mongoose.Schema<Document<ProductIndustry, any, any>, mongoose.Model<any, any, any>, undefined, any>;
