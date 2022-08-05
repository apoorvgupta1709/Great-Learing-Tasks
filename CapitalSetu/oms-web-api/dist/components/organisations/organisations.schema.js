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
exports.OrganisationSchema = exports.Organisation = void 0;
const mongoose_1 = require("@nestjs/mongoose");
const mongoose = require("mongoose");
let Organisation = class Organisation {
};
__decorate([
    mongoose_1.Prop({ required: true, enum: ['VENDOR', 'CORPORATE', 'DEALER', 'RETAILER'] }),
    __metadata("design:type", String)
], Organisation.prototype, "organisationType", void 0);
__decorate([
    mongoose_1.Prop({ enum: ['PROPRIETORSHIP', 'PARTNERSHIP', 'PVT_LTD', 'PUB_LTD'] }),
    __metadata("design:type", String)
], Organisation.prototype, "organisationEntityType", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Organisation.prototype, "name", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Organisation.prototype, "gstin", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", String)
], Organisation.prototype, "pan", void 0);
__decorate([
    mongoose_1.Prop({ required: true, ref: 'User' }),
    __metadata("design:type", mongoose.Types.ObjectId)
], Organisation.prototype, "ownerId", void 0);
__decorate([
    mongoose_1.Prop(),
    __metadata("design:type", Boolean)
], Organisation.prototype, "verified", void 0);
Organisation = __decorate([
    mongoose_1.Schema()
], Organisation);
exports.Organisation = Organisation;
exports.OrganisationSchema = mongoose_1.SchemaFactory.createForClass(Organisation);
//# sourceMappingURL=organisations.schema.js.map