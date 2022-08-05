import { KarzaService } from '../karza/karza.service';
import { ProductService } from './product.service';
export declare class ProductController {
    private readonly karzaService;
    private readonly productService;
    constructor(karzaService: KarzaService, productService: ProductService);
    testRoute(): Promise<string>;
    signup(businessType: string, mobileNumber: string, password: string): Promise<string>;
}
