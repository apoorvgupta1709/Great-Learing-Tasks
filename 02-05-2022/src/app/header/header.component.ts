import { Component, OnInit } from '@angular/core';
import { dataWether } from './data-weather';
import { Weather } from './weather';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  weather:Weather[]=[];

  constructor() { 
    this.weather=dataWether.weather;
  }

  ngOnInit(): void {
  }

  printTable(a : any){
    // alert(a)
    for(let i=1;i<=10;i++){
      console.log(i*a);
    }
  }

  findWeather(a:any){
    for(let i=0;i<this.weather.length;i++){
      if(a===this.weather[i].city){
        console.log("weather of city" + this.weather[i].weather);
        break;
      }
    }


  }



}
