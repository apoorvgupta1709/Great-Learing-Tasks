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
const products_service_1 = require("./products.service");
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
        const result = "success";
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
    async updateProduct(id, productName, productSegment, productIndustry, quantityUnit, totalQuantity, price) {
        return await this.productService.updateProduct(id, productName, productIndustry, productSegment, quantityUnit, totalQuantity, price);
    }
};
__decorate([
    common_1.Post('/bulk'),
    common_1.UseInterceptors(platform_express_1.FileInterceptor('file', {
        dest: "./uploads",
    })),
    __param(0, common_1.UploadedFile()),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "addProductBulk", null);
__decorate([
    common_1.Post(),
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
    common_1.Get(),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "getProducts", null);
__decorate([
    common_1.Get('/:id'),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "getProduct", null);
__decorate([
    common_1.Delete('/:id'),
    __param(0, common_1.Param('id')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [Object]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "deleteProduct", null);
__decorate([
    common_1.Put(),
    __param(0, common_1.Body('_id')),
    __param(1, common_1.Body('productName')),
    __param(2, common_1.Body('productSegment')),
    __param(3, common_1.Body('productIndustry')),
    __param(4, common_1.Body('quantityUnit')),
    __param(5, common_1.Body('totalQuantity')),
    __param(6, common_1.Body('price')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String, String, String, String, String, String]),
    __metadata("design:returntype", Promise)
], ProductController.prototype, "updateProduct", null);
ProductController = __decorate([
    common_1.Controller('products'),
    __metadata("design:paramtypes", [products_service_1.ProductService])
], ProductController);
exports.ProductController = ProductController;
//# sourceMappingURL=products.controller.js.map