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
exports.KarzaService = void 0;
const axios_1 = require("@nestjs/axios");
const common_1 = require("@nestjs/common");
const rxjs_1 = require("rxjs");
const config_1 = require("@nestjs/config");
let KarzaService = class KarzaService {
    constructor(httpService, configService) {
        this.httpService = httpService;
        this.configService = configService;
    }
    async verifyGst(gstin) {
        try {
            const result = await rxjs_1.firstValueFrom(this.httpService.post(this.configService.get('KARZA_VERIFY_GST_URL'), {
                gstin,
                consent: 'Y',
            }));
            return result.data;
        }
        catch (error) {
            console.error(error);
        }
    }
    async verifyPan(pan) {
        try {
            const result = await rxjs_1.firstValueFrom(this.httpService.post(this.configService.get('KARZA_VERIFY_PAN_URL'), {
                pan,
                consent: 'Y',
            }));
            return result.data;
        }
        catch (error) {
            console.error(error);
        }
    }
    async searchGstForPan(pan) {
        try {
            const result = await rxjs_1.firstValueFrom(this.httpService.post(this.configService.get('KARZA_SEARCH_GST_FOR_PAN_URL'), {
                pan,
                consent: 'Y',
            }));
            return result.data;
        }
        catch (error) {
            console.error(error);
        }
    }
    async verifyPanDetails(pan, name, dob) {
        try {
            const result = await rxjs_1.firstValueFrom(this.httpService.post(this.configService.get('KARZA_VERIFY_PAN_DETAILS_URL'), {
                pan,
                name,
                dob,
                consent: 'Y',
            }));
            return result.data;
        }
        catch (error) {
            console.error(error);
        }
    }
};
KarzaService.API_SUCCESS = 101;
KarzaService.API_AUTH_FAIL = 102;
KarzaService.API_SEARCH_FAIL = 103;
KarzaService = __decorate([
    common_1.Injectable(),
    __metadata("design:paramtypes", [axios_1.HttpService,
        config_1.ConfigService])
], KarzaService);
exports.KarzaService = KarzaService;
//# sourceMappingURL=karza.service.js.map