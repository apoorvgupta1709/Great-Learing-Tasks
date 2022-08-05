import { Model } from 'mongoose';
import { Address, AddressDocument } from './addresses.schema';
export declare class AddressesService {
    private readonly addressModel;
    constructor(addressModel: Model<AddressDocument>);
    create(address: Partial<Address>): Promise<AddressDocument>;
    findOne(query: Partial<Address>): Promise<AddressDocument>;
    deleteOne(query: Partial<Address>): Promise<{
        ok?: number;
        n?: number;
    } & {
        deletedCount?: number;
    }>;
    findOneById(id: string): Promise<AddressDocument>;
    updateOneById(id: string, update: Partial<Address>): Promise<import("mongoose").UpdateWriteOpResult>;
}
