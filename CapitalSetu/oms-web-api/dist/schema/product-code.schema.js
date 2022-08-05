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
exports.ProductCodeSchema = exports.ProductCode = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const mongoose = require("mongoose");
const organisation_schema_1 = require("./organisation.schema");
let ProductCode = class ProductCode {
};
__decorate([
    mongoose_1.Prop({ type: mongoose.Schema.Types.ObjectId, ref: 'Organisation' }),
    __metadata("design:type", organisation_schema_1.Organisation)
], ProductCode.prototype, "organisation_id", void 0);
__decorate([
    mongoose_1.Prop({ required: true }),
    __metadata("design:type", String)
], ProductCode.prototype, "code", void 0);
ProductCode = __decorate([
    mongoose_1.Schema()
], ProductCode);
exports.ProductCode = ProductCode;
exports.ProductCodeSchema = mongoose_1.SchemaFactory.createForClass(ProductCode);
//# sourceMappingURL=product-code.schema.js.map