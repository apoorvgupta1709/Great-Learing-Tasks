import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../Employee';
import { EmployeeMockData } from '../mock-employee-data';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  employees : Employee[] = [];
  idx : number =0;
  deletedEmployee!: Employee;
  //empid : number =0;
  constructor(private router:Router,private activateRoute:ActivatedRoute){
    
    this.employees = EmployeeMockData.employeeList;

  }

  ngOnInit(): void {
    this.deletedEmployee = new Employee();
    //this.employees = EmployeeMockData.employeeList;
    //this.empid = this.activateRoute.snapshot.params['id'];
    
  }

  employeeDetails(employeeId:number,empname:string){
      this.router.navigate(['employeedetails',employeeId,empname]);
  }

  updateEmployee(employeeId:number){
    this.router.navigate(['updateemployee',employeeId]);
  }

  removeEmployee(employeeId:number){
    this.searchAndFindAnEmployee(employeeId);
    if(this.idx !== -1){
      this.employees.splice(this.idx,1);
    }

  }
  searchAndFindAnEmployee(empId: number) {
    for (let i = 0; i < EmployeeMockData.employeeList.length; i++) {
      if (EmployeeMockData.employeeList[i].employeeId == empId) {
        this.deletedEmployee = EmployeeMockData.employeeList[i];
        this.idx = EmployeeMockData.employeeList.indexOf(this.deletedEmployee);
      }
    }
  }


}
