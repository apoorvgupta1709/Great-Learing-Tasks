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
exports.OnboardingController = void 0;
const common_1 = require("@nestjs/common");
const auth_user_decorator_1 = require("../../decorators/auth-user.decorator");
const auth_guard_1 = require("../../guards/auth.guard");
const basic_gst_onboarding_dto_1 = require("./dtos/basic-gst-onboarding.dto");
const basic_no_gst_onboarding_dto_1 = require("./dtos/basic-no-gst-onboarding.dto");
const onboarding_service_1 = require("./onboarding.service");
let OnboardingController = class OnboardingController {
    constructor(onboardingService) {
        this.onboardingService = onboardingService;
    }
    testRoute() {
        return 'onboarding: test route active';
    }
    async verifyGst(gstin) {
        return await this.onboardingService.verifyGst(gstin);
    }
    async verifyPan(pan) {
        return await this.onboardingService.verifyPan(pan);
    }
    async basicGstOnboarding(basicGstOnboardingDto, userId, organisationId) {
        return await this.onboardingService.basicGstOnboarding(basicGstOnboardingDto, userId, organisationId);
    }
    async basicNoGstOnboarding(basicNoGstOnboardingDto, userId, organisationId) {
        return await this.onboardingService.basicNoGstOnboarding(basicNoGstOnboardingDto, userId, organisationId);
    }
    async undoBasicGstOnboarding(userId, organisationId) {
        return await this.onboardingService.undoBasicGstOnboarding(userId, organisationId);
    }
    async undoBasicNoGstOnboarding(userId, organisationId) {
        return await this.onboardingService.undoBasicNoGstOnboarding(userId, organisationId);
    }
};
__decorate([
    common_1.Get('test'),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", []),
    __metadata("design:returntype", void 0)
], OnboardingController.prototype, "testRoute", null);
__decorate([
    common_1.Post('verify-gstin'),
    common_1.HttpCode(common_1.HttpStatus.OK),
    __param(0, common_1.Body('gstin')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "verifyGst", null);
__decorate([
    common_1.Post('verify-pan'),
    common_1.HttpCode(common_1.HttpStatus.OK),
    __param(0, common_1.Body('pan')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "verifyPan", null);
__decorate([
    common_1.Post('basic-gst'),
    common_1.HttpCode(common_1.HttpStatus.CREATED),
    __param(0, common_1.Body()),
    __param(1, auth_user_decorator_1.AuthUser('_id')),
    __param(2, auth_user_decorator_1.AuthUser('organisationId')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [basic_gst_onboarding_dto_1.BasicGstOnboardingDto, String, String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "basicGstOnboarding", null);
__decorate([
    common_1.Post('basic-no-gst'),
    common_1.HttpCode(common_1.HttpStatus.CREATED),
    __param(0, common_1.Body()),
    __param(1, auth_user_decorator_1.AuthUser('_id')),
    __param(2, auth_user_decorator_1.AuthUser('organisationId')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [basic_no_gst_onboarding_dto_1.BasicNoGstOnboardingDto, String, String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "basicNoGstOnboarding", null);
__decorate([
    common_1.Post('undo-basic-gst'),
    common_1.HttpCode(common_1.HttpStatus.OK),
    __param(0, auth_user_decorator_1.AuthUser('_id')),
    __param(1, auth_user_decorator_1.AuthUser('organisationId')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "undoBasicGstOnboarding", null);
__decorate([
    common_1.Post('undo-basic-no-gst'),
    common_1.HttpCode(common_1.HttpStatus.OK),
    __param(0, auth_user_decorator_1.AuthUser('_id')),
    __param(1, auth_user_decorator_1.AuthUser('organisationId')),
    __metadata("design:type", Function),
    __metadata("design:paramtypes", [String, String]),
    __metadata("design:returntype", Promise)
], OnboardingController.prototype, "undoBasicNoGstOnboarding", null);
OnboardingController = __decorate([
    common_1.UseGuards(auth_guard_1.AuthGuard),
    common_1.Controller('onboarding'),
    __metadata("design:paramtypes", [onboarding_service_1.OnboardingService])
], OnboardingController);
exports.OnboardingController = OnboardingController;
//# sourceMappingURL=onboarding.controller.js.map