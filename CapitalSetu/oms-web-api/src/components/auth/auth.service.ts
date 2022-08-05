import {
  ConflictException,
  Injectable,
  NotFoundException,
  UnauthorizedException,
} from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { OrganisationsService } from '../organisations/organisations.service';
import { UsersService } from '../users/users.service';
import * as bcrypt from 'bcrypt';
import * as mongoose from 'mongoose';
import * as jwt from 'jsonwebtoken';

@Injectable()
export class AuthService {
  constructor(
    private readonly usersService: UsersService,
    private readonly organisationsService: OrganisationsService,
    private readonly configService: ConfigService,
  ) {}

  async signup(mobileNumber: string, password: string, businessType: string) {
    const user = await this.usersService.findOne({ mobileNumber });
    if (user) {
      throw new ConflictException('User exists, try logging in.');
    }
    const userId = mongoose.Types.ObjectId();
    const organisationId = mongoose.Types.ObjectId();
    const hashedPassword = await this.hashPassword(password);

    const newUser = await this.usersService.create(
      {
        mobileNumber,
        password: hashedPassword,
        organisationId,
      },
      userId,
    );
    const newOrganisation = await this.organisationsService.create(
      {
        organisationType: businessType,
        ownerId: userId,
      },
      organisationId,
    );

    console.log({ newUser, newOrganisation });

    if (newUser && newOrganisation) {
      return {
        status: 'SIGNUP_SUCCESSFUL',
        message: 'Successful signup, Login to generate accessToken',
      };
    }
  }

  async login(mobileNumber: string, password: string) {
    const user = await this.usersService.findOne({ mobileNumber });
    if (!user) {
      throw new NotFoundException(`User doesn't exist, sign up.`);
    }
    const hashedPassword = user.password;
    const passwordsMatch = await this.comparePasswords(
      password,
      hashedPassword,
    );
    if (passwordsMatch) {
      // Delete the password on user document object
      const userPlainObj = user.toObject();
      delete userPlainObj.password;

      // TODO: Set auto-expiry and expiry on renewal logic of accessToken
      const accessToken = jwt.sign(
        userPlainObj,
        this.configService.get('ACCESS_TOKEN_SECRET'),
      );

      return {
        accessToken,
        status: 'LOGIN_SUCCESSFUL',
        message:
          'Successful Login, use accessToken as Bearer Token in Authorization Header',
      };
    } else {
      // Passwords don't match
      throw new UnauthorizedException('Invalid Password');
    }
  }

  private async hashPassword(password: string): Promise<string> {
    const salt = await bcrypt.genSalt();
    const hashedText = await bcrypt.hash(password, salt);
    return hashedText;
  }

  private async comparePasswords(
    password: string,
    hashedPassword: string,
  ): Promise<boolean> {
    const result = await bcrypt.compare(password, hashedPassword);
    return result;
  }
}
