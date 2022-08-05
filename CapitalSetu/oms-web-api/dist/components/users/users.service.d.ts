import { Model } from 'mongoose';
import { User, UserDocument } from './users.schema';
import * as mongoose from 'mongoose';
export declare class UsersService {
    private readonly userModel;
    constructor(userModel: Model<UserDocument>);
    create(user: Partial<User>, customUserId?: mongoose.Types.ObjectId): Promise<UserDocument>;
    findOne(query: Partial<User>): Promise<UserDocument>;
    deleteOne(query: Partial<User>): Promise<{
        ok?: number;
        n?: number;
    } & {
        deletedCount?: number;
    }>;
    findOneById(id: string): Promise<UserDocument>;
    updateOneById(id: string, update: Partial<User>): Promise<mongoose.UpdateWriteOpResult>;
}
