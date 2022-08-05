import { HttpService } from '@nestjs/axios';
import { Injectable } from '@nestjs/common';
import { firstValueFrom } from 'rxjs';
import { ConfigService } from '@nestjs/config';
@Injectable()
export class KarzaService {
  static readonly API_SUCCESS = 101;
  static readonly API_AUTH_FAIL = 102;
  static readonly API_SEARCH_FAIL = 103;

  constructor(
    private readonly httpService: HttpService,
    private readonly configService: ConfigService,
  ) {}

  async verifyGst(gstin: string) {
    try {
      const result = await firstValueFrom(
        this.httpService.post(this.configService.get('KARZA_VERIFY_GST_URL'), {
          gstin,
          consent: 'Y',
        }),
      );
      return result.data;
    } catch (error) {
      console.error(error);
    }
  }

  async verifyPan(pan) {
    try {
      const result = await firstValueFrom(
        this.httpService.post(this.configService.get('KARZA_VERIFY_PAN_URL'), {
          pan,
          consent: 'Y',
        }),
      );
      return result.data;
    } catch (error) {
      console.error(error);
    }
  }

  async searchGstForPan(pan) {
    try {
      const result = await firstValueFrom(
        this.httpService.post(
          this.configService.get('KARZA_SEARCH_GST_FOR_PAN_URL'),
          {
            pan,
            consent: 'Y',
          },
        ),
      );
      return result.data;
    } catch (error) {
      console.error(error);
    }
  }

  async verifyPanDetails(pan: string, name: string, dob: string) {
    try {
      const result = await firstValueFrom(
        this.httpService.post(
          this.configService.get('KARZA_VERIFY_PAN_DETAILS_URL'),
          {
            pan,
            name,
            dob,
            consent: 'Y',
          },
        ),
      );
      return result.data;
    } catch (error) {
      console.error(error);
    }
  }
}
