import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { User, UserDocument } from './users.schema';
import * as mongoose from 'mongoose';

@Injectable()
export class UsersService {
  constructor(
    @InjectModel('User') private readonly userModel: Model<UserDocument>,
  ) {}

  async create(
    user: Partial<User>,
    customUserId: mongoose.Types.ObjectId = null,
  ) {
    const newUser = new this.userModel(user);
    if (customUserId) {
      newUser._id = customUserId;
    }
    return await newUser.save();
  }

  async findOne(query: Partial<User>) {
    return await this.userModel.findOne(query).exec();
  }

  async deleteOne(query: Partial<User>) {
    return await this.userModel.deleteOne(query).exec();
  }

  async findOneById(id: string) {
    return await this.userModel.findById(id).exec();
  }

  async updateOneById(id: string, update: Partial<User>) {
    return await this.userModel.updateOne({ _id: id }, update).exec();
  }
}
