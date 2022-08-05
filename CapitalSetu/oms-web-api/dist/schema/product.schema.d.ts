import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type ProductDocument = Product & Document;
export declare class Product {
    organisation_id: Organisation;
    name: string;
    code_id: string;
    segment_id: string;
    industry_id: string;
    unit_id: string;
    price: string;
    in_stock: boolean;
}
export declare const ProductSchema: mongoose.Schema<Document<Product, any, any>, mongoose.Model<any, any, any>, undefined, any>;
