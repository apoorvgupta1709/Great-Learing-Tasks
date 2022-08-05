import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Address, AddressDocument } from './addresses.schema';

@Injectable()
export class AddressesService {
  constructor(
    @InjectModel('Address')
    private readonly addressModel: Model<AddressDocument>,
  ) {}

  async create(address: Partial<Address>) {
    const newAddress = new this.addressModel(address);
    return await newAddress.save();
  }

  async findOne(query: Partial<Address>) {
    return await this.addressModel.findOne(query).exec();
  }

  async deleteOne(query: Partial<Address>) {
    return await this.addressModel.deleteOne(query).exec();
  }

  async findOneById(id: string) {
    return await this.addressModel.findById(id).exec();
  }

  async updateOneById(id: string, update: Partial<Address>) {
    return await this.addressModel.updateOne({ _id: id }, update).exec();
  }
}
