interface shape { 
    draw();    
 } 

    class Rectangle implements shape{  
     draw(){
         console.log("drawing rectangle");
        }  
    }  
    class Circle implements shape{  
     draw(){
        console.log("drawing circle");
        }  
    }  
  
 let objec = new Circle();  
 console.log(objec.draw);

 let objec2 = new Rectangle();  
 console.log(objec2.draw);

 