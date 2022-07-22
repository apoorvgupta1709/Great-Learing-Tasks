import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from '../Employee';
import { EmployeeMockData } from '../mock-employee-data';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee!: Employee;
  
  constructor(private router:Router) { 
    this.employee = new Employee();
  }

  ngOnInit(): void {
  }

  onSubmit(){
    this.addEmployee();
  }

  addEmployee(){
 
    EmployeeMockData.employeeList.push(this.employee);
    this.goToList();
  }

  goToList() {
    this.router.navigate(['']);
  }

}
