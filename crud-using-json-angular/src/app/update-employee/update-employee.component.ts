import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../Employee';
import { EmployeeMockData } from '../mock-employee-data';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  updatedEmployee!: Employee;
  empId : number =0;
  index : number = 0;
  constructor(
    private router : Router,
    private activateRoute : ActivatedRoute) { 
    
  }

  ngOnInit(): void {
    this.updatedEmployee = new Employee();
    //this.employees = EmployeeMockData.employeeList;
    this.empId = this.activateRoute.snapshot.params['id'];
    this.searchAndFindAnEmployee(this.empId);
  }

  onSubmit(){
    this.updateEmployeeMethod();
  }
  searchAndFindAnEmployee(empId: number) {
    for (let i = 0; i < EmployeeMockData.employeeList.length; i++) {
      if (EmployeeMockData.employeeList[i].employeeId == empId) {
        this.updatedEmployee = EmployeeMockData.employeeList[i];
        this.index = EmployeeMockData.employeeList.indexOf(this.updatedEmployee);
      }
    }
  }
  updateEmployeeMethod() {
    if(this.index !== -1)
    {
     EmployeeMockData.employeeList[this.index] = this.updatedEmployee;
    }
    this.goToList();
  }
  goToList() {
    this.router.navigate(['']);
  }


}
