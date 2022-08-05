import {
  ConflictException,
    Injectable,
  } from '@nestjs/common';
import { Product, ProdDocument } from './products.schema';
import { Model } from 'mongoose';
import { InjectModel } from '@nestjs/mongoose';


 
  @Injectable()
export class ProductService {
 
 

  constructor(
    @InjectModel('Product') private readonly productModel: Model<ProdDocument>,
  ) {}

  async addBulk(users: any) {
   await this.productModel.insertMany(users);    
  }

  async add(productName: string, productIndustry: string, productSegment: string, quantityUnit: string, totalQuantity: string, price: string) {
    const prod = await this.findOne({ productName });
    if (prod) {
      throw new ConflictException('Product exists, try different one.');
    }
    
    await this.create({
      productName,
      productIndustry,
      productSegment,
      quantityUnit,
      totalQuantity,
      price,
    });
    return {
      productName,
      quantityUnit,
    };

  }

  async getProducts() {
    return await this.productModel.find().exec();
  }

  async getProduct(_id : string) {
    
    const prod= await this.productModel.findOne({ _id });
    if (!prod) {
      throw new ConflictException('Product does not  exists, try different one.');
    }
    return prod;
    
  }

  async deleteProduct(_id: string) {
    const prod=await this.productModel.deleteOne({ _id });
    if (prod.deletedCount==0) {
      throw new ConflictException('Product does not exists, try different one.');
    }
    var result="success";

     return {
       result,
      };

  }

  async updateProduct(_id: string,productName: string, productIndustry: string, productSegment: string, quantityUnit: string, totalQuantity: string, price: string) {
    const prod= await this.productModel.findOne({ _id });
    if (!prod) {
      throw new ConflictException('Product does not  exists, try adding product.');
    }

    const update = {  productName,
      productIndustry,
      productSegment,
      quantityUnit,
      totalQuantity,
      price,};
     await prod.updateOne(update);
     return {
      productName
     }; 

  } 


  async create(
    product: Partial<Product>,
  ) {
    const newProduct = new this.productModel(product);
    const result = await newProduct.save();
    console.log(result);
  }
  
  async findOne(query: Partial<Product>) {
    return await this.productModel.findOne(query).exec();
  }

}
