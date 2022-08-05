import { Module } from '@nestjs/common';
import { HttpModule } from '@nestjs/axios';
import { KarzaService } from './karza.service';
import { ConfigService } from '@nestjs/config';

@Module({
  imports: [
    HttpModule.registerAsync({
      useFactory: (configService: ConfigService) => ({
        headers: {
          'x-karza-key': configService.get('KARZA_KEY'),
        },
      }),
      inject: [ConfigService],
    }),
  ],
  providers: [KarzaService],
  exports: [KarzaService],
})
export class KarzaModule {}
