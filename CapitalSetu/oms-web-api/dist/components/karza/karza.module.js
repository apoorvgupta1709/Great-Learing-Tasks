"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.KarzaModule = void 0;
const common_1 = require("@nestjs/common");
const axios_1 = require("@nestjs/axios");
const karza_service_1 = require("./karza.service");
const config_1 = require("@nestjs/config");
let KarzaModule = class KarzaModule {
};
KarzaModule = __decorate([
    common_1.Module({
        imports: [
            axios_1.HttpModule.registerAsync({
                useFactory: (configService) => ({
                    headers: {
                        'x-karza-key': configService.get('KARZA_KEY'),
                    },
                }),
                inject: [config_1.ConfigService],
            }),
        ],
        providers: [karza_service_1.KarzaService],
        exports: [karza_service_1.KarzaService],
    })
], KarzaModule);
exports.KarzaModule = KarzaModule;
//# sourceMappingURL=karza.module.js.map