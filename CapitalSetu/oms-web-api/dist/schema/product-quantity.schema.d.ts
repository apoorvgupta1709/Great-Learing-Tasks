import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type ProductQuantityDocument = ProductQuantity & Document;
export declare class ProductQuantity {
    organisation_id: Organisation;
    quantity: string;
}
export declare const ProductQuantitySchema: mongoose.Schema<Document<ProductQuantity, any, any>, mongoose.Model<any, any, any>, undefined, any>;
