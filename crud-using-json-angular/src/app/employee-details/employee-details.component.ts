import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from '../Employee';
import { EmployeeMockData } from '../mock-employee-data';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  employee!: Employee;
  employees: Employee[] = [];
  empId: number = 0;
  empName : string = '';
  constructor(
    private router: Router,
    private activateRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.employees = EmployeeMockData.employeeList;
    this.employee = new Employee();
    this.empId = this.activateRoute.snapshot.params['empid'];
    this.empName = this.activateRoute.snapshot.params['empname'];
    this.searchAndFindAnEmployee(this.empId);
  }

  searchAndFindAnEmployee(empId: number) {
    for (let i = 0; i < this.employees.length; i++) {
      if (this.employees[i].employeeId == empId) {
        this.employee = this.employees[i];
      }
    }
  }

  gotolist(){
    this.router.navigate([''])
  }

}
