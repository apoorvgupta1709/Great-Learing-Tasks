import { Document } from 'mongoose';
import * as mongoose from 'mongoose';
import { Organisation } from './organisation.schema';
export declare type ProductSegmentDocument = ProductSegment & Document;
export declare class ProductSegment {
    organisation_id: Organisation;
    segment: string;
}
export declare const ProductSegmentSchema: mongoose.Schema<Document<ProductSegment, any, any>, mongoose.Model<any, any, any>, undefined, any>;
