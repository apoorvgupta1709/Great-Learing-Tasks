import {
    Body,
    Controller,
    HttpCode,
    Get,
    HttpStatus,
    Post,
    Param,
    Delete,
    Put,
    UseInterceptors,
    UploadedFile,
  } from '@nestjs/common';
import { FileInterceptor } from '@nestjs/platform-express';
  import { ProductService } from './products.service';
  
@Controller('products')
export class ProductController {
  constructor(
    private readonly productService: ProductService,
  ) {}


  @Post('/bulk')
  @UseInterceptors(FileInterceptor('file', {
    dest:"./uploads",
  }))
  async addProductBulk(
    @UploadedFile() file: Express.Multer.File
  ) {

    const fileName= file.filename;
    const CSVToJSON = require('csvtojson');
    CSVToJSON().fromFile('./uploads/' + fileName)
    .then(users => {

        this.productService.addBulk(users);

        var fs = require('fs');
        fs.unlink('./uploads/' + fileName, function (err) {
        if (err) throw err;
          });
    }).catch(err => {
        console.log(err);
    });   


    const result="success";
    return{
      result,
    };

  }



  @Post()
  @HttpCode(HttpStatus.CREATED)
  async addProduct(
    @Body('productName') productName: string,
    @Body('productSegment') productSegment: string,
    @Body('productIndustry') productIndustry: string,
    @Body('quantityUnit') quantityUnit: string,
    @Body('totalQuantity') totalQuantity: string,
    @Body('price') price: string,
  ) {
    return await this.productService.add(productName,productIndustry,productSegment,quantityUnit,totalQuantity,price);

  }

  @Get()
  async getProducts() {
    return await this.productService.getProducts();
    
  }

  @Get('/:id')
  async getProduct(@Param('id') productName) {

    return await this.productService.getProduct(productName);
    
  }

  @Delete('/:id')
  async deleteProduct(@Param('id') productName) {

    return await this.productService.deleteProduct(productName);
    
  }

  @Put()
  async updateProduct(
    @Body('_id') id: string,
    @Body('productName') productName: string,
    @Body('productSegment') productSegment: string,
    @Body('productIndustry') productIndustry: string,
    @Body('quantityUnit') quantityUnit: string,
    @Body('totalQuantity') totalQuantity: string,
    @Body('price') price: string,
  ) {
    return await this.productService.updateProduct(id,productName,productIndustry,productSegment,quantityUnit,totalQuantity,price);

  }

}
