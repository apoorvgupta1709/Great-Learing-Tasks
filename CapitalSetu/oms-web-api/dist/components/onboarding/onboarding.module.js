"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.OnboardingModule = void 0;
const common_1 = require("@nestjs/common");
const addresses_module_1 = require("../addresses/addresses.module");
const karza_module_1 = require("../karza/karza.module");
const organisations_module_1 = require("../organisations/organisations.module");
const users_module_1 = require("../users/users.module");
const onboarding_controller_1 = require("./onboarding.controller");
const onboarding_service_1 = require("./onboarding.service");
let OnboardingModule = class OnboardingModule {
};
OnboardingModule = __decorate([
    common_1.Module({
        imports: [karza_module_1.KarzaModule, users_module_1.UsersModule, organisations_module_1.OrganisationsModule, addresses_module_1.AddressesModule],
        controllers: [onboarding_controller_1.OnboardingController],
        providers: [onboarding_service_1.OnboardingService],
    })
], OnboardingModule);
exports.OnboardingModule = OnboardingModule;
//# sourceMappingURL=onboarding.module.js.map