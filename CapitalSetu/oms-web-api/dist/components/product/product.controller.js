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
const platform_express_1 = require("@nestjs/platform-express");
const product_service_1 = require("./product.service");
let ProductController = class ProductController {
    constructor(productService) {
        this.productService = productService;
    }
    async addProductBulk(file) {
        const fileName = file.filename;
        const CSVToJSON = require('csvtojson');
        CSVToJSON().fromFile('./uploads/' + fileName)
            .then(users => {
            this.productService.addBulk(users);
            var fs = require('fs');
            fs.unlink('./uploads/' + fileName, function (err) {
                if (err)
                    throw err;
            });
        }).catch(err => {
            console.log(err);
        });
        var result = "success";
        return {
            result,
        };
    }
    async addProduct(productName, productSegment, productIndustry, quantityUnit, totalQuantity, price) {
        return await this.productService.add(productName, productIndustry, productSegment, quantityUnit, totalQuantity, price);
    }
    async getProducts() {
        return await this.productService.getProducts();
    }
    async getProduct(productName) {
        return await this.productService.getProduct(productName);
    }
    async deleteProduct(productName) {
        return await this.productService.deleteProduct(productName);
    }
    async updateProduct(productName, productSegment, productIndustry, quantityUnit, totalQuantity, price) {
        return await this.productService.updateProduct(productName, productIndustry, productSegment, quantityUnit, totalQuantity, price);
    }
};
__decorate([
    common_1.Post('product/bulk'),
    common_1.UseInterceptors(platform_express_1.FileInterceptor('file', {
        dest: "./uploads",
    })),
    __param(0, common_1.UploadedFile()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "addProductBulk", null);
__decorate([
    common_1.Post('product'),
    common_1.HttpCode(common_1.HttpStatus.CREATED),
    __param(0, common_1.Body('productName')),
    __param(1, common_1.Body('productSegment')),
    __param(2, common_1.Body('productIndustry')),
    __param(3, common_1.Body('quantityUnit')),
    __param(4, common_1.Body('totalQuantity')),
    __param(5, common_1.Body('price')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String, String, String, String, String]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "addProduct", null);
__decorate([
    common_1.Get('product'),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "getProducts", null);
__decorate([
    common_1.Get('product/:productName'),
    __param(0, common_1.Param('productName')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "getProduct", null);
__decorate([
    common_1.Delete('product/:productName'),
    __param(0, common_1.Param('productName')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "deleteProduct", null);
__decorate([
    common_1.Put('product'),
    __param(0, common_1.Body('productName')),
    __param(1, common_1.Body('productSegment')),
    __param(2, common_1.Body('productIndustry')),
    __param(3, common_1.Body('quantityUnit')),
    __param(4, common_1.Body('totalQuantity')),
    __param(5, common_1.Body('price')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String, String, String, String, String]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "updateProduct", null);
ProductController = __decorate([
    common_1.Controller(),
    __metadata("design:paramtypes", [product_service_1.ProductService])
], ProductController);
exports.ProductController = ProductController;
//# sourceMappingURL=product.controller.js.map