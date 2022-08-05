"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.OrganisationsModule = void 0;
const common_1 = require("@nestjs/common");
const mongoose_1 = require("@nestjs/mongoose");
const organisations_schema_1 = require("./organisations.schema");
const organisations_service_1 = require("./organisations.service");
let OrganisationsModule = class OrganisationsModule {
};
OrganisationsModule = __decorate([
    common_1.Module({
        imports: [
            mongoose_1.MongooseModule.forFeature([
                {
                    name: 'Organisation',
                    schema: organisations_schema_1.OrganisationSchema,
                },
            ]),
        ],
        providers: [organisations_service_1.OrganisationsService],
        exports: [organisations_service_1.OrganisationsService],
    })
], OrganisationsModule);
exports.OrganisationsModule = OrganisationsModule;
//# sourceMappingURL=organisations.module.js.map