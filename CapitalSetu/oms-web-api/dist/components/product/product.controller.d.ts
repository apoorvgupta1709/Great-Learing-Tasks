/// <reference types="multer" />
import { ProductService } from './product.service';
export declare class ProductController {
    private readonly productService;
    constructor(productService: ProductService);
    addProductBulk(file: Express.Multer.File): Promise<{
        result: string;
    }>;
    addProduct(productName: string, productSegment: string, productIndustry: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
        quantityUnit: string;
    }>;
    getProducts(): Promise<import("./product.schema").ProdDocument[]>;
    getProduct(productName: any): Promise<import("./product.schema").ProdDocument>;
    deleteProduct(productName: any): Promise<{
        productName: string;
    }>;
    updateProduct(productName: string, productSegment: string, productIndustry: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
    }>;
}
