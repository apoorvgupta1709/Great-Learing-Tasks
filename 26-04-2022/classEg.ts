class Song {
     
     title : string;
     singer : string;
     album : string;
    
    constructor(title:string,singer:string,album:string) { 
        this.title = title; 
        this.singer = singer; 
        this.album = album ;

     }  

    

  
}

var obj = new Song("One","s1","a1");
var obj2 = new Song("two","s2","a2");
var obj3 = new Song("three","s3","a3");

var arr:Song[]; 
arr=[obj,obj2,obj3];

console.log(arr);

