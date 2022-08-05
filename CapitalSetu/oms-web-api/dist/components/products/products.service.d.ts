import { Product, ProdDocument } from './products.schema';
import { Model } from 'mongoose';
export declare class ProductService {
    private readonly productModel;
    constructor(productModel: Model<ProdDocument>);
    addBulk(users: any): Promise<void>;
    add(productName: string, productIndustry: string, productSegment: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
        quantityUnit: string;
    }>;
    getProducts(): Promise<ProdDocument[]>;
    getProduct(_id: string): Promise<ProdDocument>;
    deleteProduct(_id: string): Promise<{
        result: string;
    }>;
    updateProduct(_id: string, productName: string, productIndustry: string, productSegment: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
    }>;
    create(product: Partial<Product>): Promise<void>;
    findOne(query: Partial<Product>): Promise<ProdDocument>;
}
