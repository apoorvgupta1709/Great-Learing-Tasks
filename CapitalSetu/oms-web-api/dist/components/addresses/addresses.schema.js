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
exports.AddressSchema = exports.Address = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const mongoose = require("mongoose");
let Address = class Address {
};
__decorate([
    mongoose_1.Prop({
        required: true,
        ref: 'Organisation',
    }),
    __metadata("design:type", mongoose.Types.ObjectId)
], Address.prototype, "organisationId", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "line1", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "line2", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "landmark", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "city", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "town", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Address.prototype, "pinCode", void 0);
__decorate([
    mongoose_1.Prop({ default: true }),
    __metadata("design:type", Boolean)
], Address.prototype, "isDefault", void 0);
Address = __decorate([
    mongoose_1.Schema()
], Address);
exports.Address = Address;
exports.AddressSchema = mongoose_1.SchemaFactory.createForClass(Address);
//# sourceMappingURL=addresses.schema.js.map