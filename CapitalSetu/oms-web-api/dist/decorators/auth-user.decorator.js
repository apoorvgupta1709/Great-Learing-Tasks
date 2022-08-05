"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.AuthUser = void 0;
const common_1 = require("@nestjs/common");
exports.AuthUser = common_1.createParamDecorator((data, context) => {
    const request = context.switchToHttp().getRequest();
    const user = request.user;
    return data ? user === null || user === void 0 ? void 0 : user[data] : user;
});
//# sourceMappingURL=auth-user.decorator.js.map