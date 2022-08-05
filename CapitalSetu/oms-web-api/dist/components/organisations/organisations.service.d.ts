import { Model } from 'mongoose';
import { Organisation, OrganisationDocument } from './organisations.schema';
import * as mongoose from 'mongoose';
export declare class OrganisationsService {
    private readonly organisationModel;
    constructor(organisationModel: Model<OrganisationDocument>);
    create(organisation: Partial<Organisation>, customOrganisationId?: mongoose.Types.ObjectId): Promise<OrganisationDocument>;
    findOne(query: Partial<Organisation>): Promise<OrganisationDocument>;
    deleteOne(query: Partial<Organisation>): Promise<{
        ok?: number;
        n?: number;
    } & {
        deletedCount?: number;
    }>;
    findOneById(id: string): Promise<OrganisationDocument>;
    updateOneById(id: string, update: Partial<Organisation>): Promise<mongoose.UpdateWriteOpResult>;
}
