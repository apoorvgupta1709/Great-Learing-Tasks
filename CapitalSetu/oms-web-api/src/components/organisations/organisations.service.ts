import { Injectable } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { Organisation, OrganisationDocument } from './organisations.schema';
import * as mongoose from 'mongoose';

@Injectable()
export class OrganisationsService {
  constructor(
    @InjectModel('Organisation')
    private readonly organisationModel: Model<OrganisationDocument>,
  ) {}

  async create(
    organisation: Partial<Organisation>,
    customOrganisationId: mongoose.Types.ObjectId = null,
  ) {
    const newOrganisation = new this.organisationModel(organisation);
    if (customOrganisationId) {
      newOrganisation._id = customOrganisationId;
    }
    return await newOrganisation.save();
  }

  async findOne(query: Partial<Organisation>) {
    return await this.organisationModel.findOne(query).exec();
  }

  async deleteOne(query: Partial<Organisation>) {
    return await this.organisationModel.deleteOne(query).exec();
  }

  async findOneById(id: string) {
    return await this.organisationModel.findById(id).exec();
  }

  async updateOneById(id: string, update: Partial<Organisation>) {
    return await this.organisationModel.updateOne({ _id: id }, update).exec();
  }
}
