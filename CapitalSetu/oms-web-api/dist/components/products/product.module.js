"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.ProductModule = void 0;
const common_1 = require("@nestjs/common");
const auth_service_1 = require("../auth/auth.service");
const karza_module_1 = require("../karza/karza.module");
const organisations_module_1 = require("../organisations/organisations.module");
const users_module_1 = require("../users/users.module");
const product_controller_1 = require("./product.controller");
let ProductModule = class ProductModule {
};
ProductModule = __decorate([
    common_1.Module({
        imports: [karza_module_1.KarzaModule, users_module_1.UsersModule, organisations_module_1.OrganisationsModule],
        controllers: [product_controller_1.ProductController],
        providers: [auth_service_1.AuthService],
    })
], ProductModule);
exports.ProductModule = ProductModule;
//# sourceMappingURL=product.module.js.map