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
exports.ProductService = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("mongoose");
const mongoose_2 = require("@nestjs/mongoose");
let ProductService = class ProductService {
    constructor(productModel) {
        this.productModel = productModel;
    }
    async addBulk(users) {
        await this.productModel.insertMany(users);
    }
    async add(productName, productIndustry, productSegment, quantityUnit, totalQuantity, price) {
        const prod = await this.findOne({ productName });
        if (prod) {
            throw new common_1.ConflictException('Product exists, try different one.');
        }
        await this.create({
            productName,
            productIndustry,
            productSegment,
            quantityUnit,
            totalQuantity,
            price,
        });
        return {
            productName,
            quantityUnit,
        };
    }
    async getProducts() {
        return await this.productModel.find().exec();
    }
    async getProduct(productName) {
        const prod = await this.findOne({ productName });
        if (!prod) {
            throw new common_1.ConflictException('Product does not  exists, try different one.');
        }
        return prod;
    }
    async deleteProduct(productName) {
        const prod = await this.productModel.deleteOne({ productName });
        if (prod.deletedCount == 0) {
            throw new common_1.ConflictException('Product does not exists, try different one.');
        }
        return {
            productName
        };
    }
    async updateProduct(productName, productIndustry, productSegment, quantityUnit, totalQuantity, price) {
        const prod = await this.findOne({ productName });
        if (!prod) {
            throw new common_1.ConflictException('Product does not  exists, try adding product.');
        }
        const update = { productName,
            productIndustry,
            productSegment,
            quantityUnit,
            totalQuantity,
            price, };
        await prod.updateOne(update);
        return {
            productName
        };
    }
    async create(product) {
        const newProduct = new this.productModel(product);
        const result = await newProduct.save();
        console.log(result);
    }
    async findOne(query) {
        return await this.productModel.findOne(query).exec();
    }
};
ProductService = __decorate([
    common_1.Injectable(),
    __param(0, mongoose_2.InjectModel('Product')),
    __metadata("design:paramtypes", [mongoose_1.Model])
], ProductService);
exports.ProductService = ProductService;
//# sourceMappingURL=product.service.js.map