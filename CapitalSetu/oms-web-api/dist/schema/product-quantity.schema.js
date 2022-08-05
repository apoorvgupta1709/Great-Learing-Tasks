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
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductQuantitySchema = exports.ProductQuantity = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const mongoose = require("mongoose");
const organisation_schema_1 = require("./organisation.schema");
let ProductQuantity = class ProductQuantity {
};
__decorate([
    mongoose_1.Prop({ type: mongoose.Schema.Types.ObjectId, ref: 'Organisation' }),
    __metadata("design:type", organisation_schema_1.Organisation)
], ProductQuantity.prototype, "organisation_id", void 0);
__decorate([
    mongoose_1.Prop({ required: true }),
    __metadata("design:type", String)
], ProductQuantity.prototype, "quantity", void 0);
ProductQuantity = __decorate([
    mongoose_1.Schema()
], ProductQuantity);
exports.ProductQuantity = ProductQuantity;
exports.ProductQuantitySchema = mongoose_1.SchemaFactory.createForClass(ProductQuantity);
//# sourceMappingURL=product-quantity.schema.js.map