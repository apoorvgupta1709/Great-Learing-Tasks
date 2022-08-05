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
exports.AuthService = void 0;
const common_1 = require("@nestjs/common");
const config_1 = require("@nestjs/config");
const organisations_service_1 = require("../organisations/organisations.service");
const users_service_1 = require("../users/users.service");
const bcrypt = require("bcrypt");
const mongoose = require("mongoose");
const jwt = require("jsonwebtoken");
let AuthService = class AuthService {
    constructor(usersService, organisationsService, configService) {
        this.usersService = usersService;
        this.organisationsService = organisationsService;
        this.configService = configService;
    }
    async signup(mobileNumber, password, businessType) {
        const user = await this.usersService.findOne({ mobileNumber });
        if (user) {
            throw new common_1.ConflictException('User exists, try logging in.');
        }
        const userId = mongoose.Types.ObjectId();
        const organisationId = mongoose.Types.ObjectId();
        const hashedPassword = await this.hashPassword(password);
        const newUser = await this.usersService.create({
            mobileNumber,
            password: hashedPassword,
            organisationId,
        }, userId);
        const newOrganisation = await this.organisationsService.create({
            organisationType: businessType,
            ownerId: userId,
        }, organisationId);
        console.log({ newUser, newOrganisation });
        if (newUser && newOrganisation) {
            return {
                status: 'SIGNUP_SUCCESSFUL',
                message: 'Successful signup, Login to generate accessToken',
            };
        }
    }
    async login(mobileNumber, password) {
        const user = await this.usersService.findOne({ mobileNumber });
        if (!user) {
            throw new common_1.NotFoundException(`User doesn't exist, sign up.`);
        }
        const hashedPassword = user.password;
        const passwordsMatch = await this.comparePasswords(password, hashedPassword);
        if (passwordsMatch) {
            const userPlainObj = user.toObject();
            delete userPlainObj.password;
            const accessToken = jwt.sign(userPlainObj, this.configService.get('ACCESS_TOKEN_SECRET'));
            return {
                accessToken,
                status: 'LOGIN_SUCCESSFUL',
                message: 'Successful Login, use accessToken as Bearer Token in Authorization Header',
            };
        }
        else {
            throw new common_1.UnauthorizedException('Invalid Password');
        }
    }
    async hashPassword(password) {
        const salt = await bcrypt.genSalt();
        const hashedText = await bcrypt.hash(password, salt);
        return hashedText;
    }
    async comparePasswords(password, hashedPassword) {
        const result = await bcrypt.compare(password, hashedPassword);
        return result;
    }
};
AuthService = __decorate([
    common_1.Injectable(),
    __metadata("design:paramtypes", [users_service_1.UsersService,
        organisations_service_1.OrganisationsService,
        config_1.ConfigService])
], AuthService);
exports.AuthService = AuthService;
//# sourceMappingURL=auth.service.js.map