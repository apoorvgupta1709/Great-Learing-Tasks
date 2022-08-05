import { Product, ProdDocument } from './product.schema';
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
    getProduct(productName: string): Promise<ProdDocument>;
    deleteProduct(productName: string): Promise<{
        productName: string;
    }>;
    updateProduct(productName: string, productIndustry: string, productSegment: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
    }>;
    create(product: Partial<Product>): Promise<void>;
    findOne(query: Partial<Product>): Promise<ProdDocument>;
}
