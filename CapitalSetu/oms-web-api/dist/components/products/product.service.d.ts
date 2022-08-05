import { OrganisationsService } from '../organisations/organisations.service';
import { UsersService } from '../users/users.service';
export declare class ProductService {
    private readonly usersService;
    private readonly organisationsService;
    constructor(usersService: UsersService, organisationsService: OrganisationsService);
    test(): Promise<void>;
}
