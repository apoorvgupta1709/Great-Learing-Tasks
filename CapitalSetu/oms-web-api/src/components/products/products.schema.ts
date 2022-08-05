import { Prop, Schema, SchemaFactory } from '@nestjs/mongoose';
import { Document } from 'mongoose';


export type ProdDocument = Product & Document;

@Schema()
export class Product {
  @Prop()
  productName: string;

  @Prop()
  productSegment: string;

  @Prop()
  productIndustry: string;
  
  @Prop()
  quantityUnit: string;

  @Prop()
  totalQuantity: string;
  
  @Prop()
  price: string;
  
}

export const ProductSchema = SchemaFactory.createForClass(Product);
