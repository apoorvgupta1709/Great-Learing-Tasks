"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductController = void 0;
const common_1 = require("@nestjs/common");
const karza_service_1 = require("../karza/karza.service");
const product_service_1 = require("./product.service");
let ProductController = class ProductController {
    constructor(karzaService, productService) {
        this.karzaService = karzaService;
        this.productService = productService;
    }
    async testRoute() {
        await this.productService.test();
        return 'auth: test route are active';
    }
    async signup(businessType, mobileNumber, password) {
        await this.productService.test();
        return 'this is cotroller';
    }
};
__decorate([
    common_1.Get('test'),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "testRoute", null);
__decorate([
    common_1.Post('signup'),
    common_1.HttpCode(common_1.HttpStatus.CREATED),
    __param(0, common_1.Body('businessType')),
    __param(1, common_1.Body('mobileNumber')),
    __param(2, common_1.Body('password')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String, String]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "signup", null);
ProductController = __decorate([
    common_1.Controller('product'),
    __metadata("design:paramtypes", [karza_service_1.KarzaService,
        product_service_1.ProductService])
], ProductController);
exports.ProductController = ProductController;
//# sourceMappingURL=product.controller.js.map