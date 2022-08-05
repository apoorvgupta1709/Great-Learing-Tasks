import { Document } from 'mongoose';
export declare type ProdDocument = Product & Document;
export declare class Product {
    productName: string;
    productSegment: string;
    productIndustry: string;
    quantityUnit: string;
    totalQuantity: string;
    price: string;
}
export declare const ProductSchema: import("mongoose").Schema<Document<Product, any, any>, import("mongoose").Model<any, any, any>, undefined, any>;
