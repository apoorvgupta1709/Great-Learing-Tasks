var Employee = /** @class */ (function () {
    // constructor(){
    // } 
    function Employee(id, userName, email, pass) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.pass = pass;
    }
    return Employee;
}());
var EmployeeOperations = /** @class */ (function () {
    function EmployeeOperations() {
    }
    EmployeeOperations.prototype.addEmployee = function () {
        var id = window.prompt("Enter id");
        var username = window.prompt("Enter username");
        var email = window.prompt("Enter email");
        var pass = window.prompt("Enter pass");
        var obj = new Employee(id, username, email, pass);
        this.employeeList.push(obj);
        console.log("add");
        console.log(this.employeeList);
    };
    EmployeeOperations.prototype.updateEmployee = function () {
        console.log("update");
    };
    EmployeeOperations.prototype.deleteEmployee = function () {
        console.log("delete");
    };
    return EmployeeOperations;
}());
var po = new EmployeeOperations();
var input = window.prompt("Enter the input 1.add 2.delete 3.update");
switch (input) {
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
