/// <reference types="multer" />
import { ProductService } from './products.service';
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
    getProducts(): Promise<import("./products.schema").ProdDocument[]>;
    getProduct(productName: any): Promise<import("./products.schema").ProdDocument>;
    deleteProduct(productName: any): Promise<{
        result: string;
    }>;
    updateProduct(id: string, productName: string, productSegment: string, productIndustry: string, quantityUnit: string, totalQuantity: string, price: string): Promise<{
        productName: string;
    }>;
}
