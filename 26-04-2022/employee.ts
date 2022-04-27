class Employee{

    id : string;
    userName : string;
    email : string;
    pass : string;


    // constructor(){

    // } 
       constructor(id:string,userName:string,email:string,pass:string) { 
       this.id = id; 
       this.userName = userName; 
       this.email = email ;
       this.pass = pass ;
    } 

}


    class EmployeeOperations{
   public employeeList: Employee[];


    public addEmployee() : void{

        const id=window.prompt("Enter id");
        const username=window.prompt("Enter username");
        const email=window.prompt("Enter email");
        const pass=window.prompt("Enter pass");

     var obj = new Employee(id,username,email,pass);
     this.employeeList.push(obj);

    console.log("add");
    console.log(this.employeeList);

    }
    public updateEmployee() : void{
    console.log("update");
    }
    public deleteEmployee() : void{
    console.log("delete");
    }
    }
    const po = new EmployeeOperations();

    const input = window.prompt("Enter the input 1.add 2.delete 3.update");
    switch(input){
    case '1':
    po.addEmployee();
    break;
    case '2':
    po.deleteEmployee();
    break;
    case '3':
    po.updateEmployee();
    break;
    }



// function add(){
//     var obj = new Employee("","s1","a1","");


// }
