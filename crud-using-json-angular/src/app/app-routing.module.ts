import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { ContactusComponent } from './contactus/contactus.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { HomePageComponent } from './home-page/home-page.component';
import { UpdateEmployeeComponent } from   './update-employee/update-employee.component';

const routes: Routes = [
  {path:'addemployee',component:AddEmployeeComponent},
  {path:'updateemployee/:id',component:UpdateEmployeeComponent},
  {path:'employeedetails/:empid/:empname',component:EmployeeDetailsComponent},
  {path:'',component:EmployeeListComponent},
  {path:'contact',component:ContactusComponent},
  {path:'home',component:HomePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
