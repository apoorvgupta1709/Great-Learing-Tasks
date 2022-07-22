import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { UserserviceService } from '../service/userservice.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private bar: MatSnackBar, private service: UserserviceService, private router: Router,
  ) { }
  name: string = '';
  email: string = '';
  password: string = '';
  register(): void {
    this.service
      .register({
        name: this.name,
        email: this.email,
        password: this.password,
      })
      .then((res) => {

        if (this.name.length >= 1) { }
        else {
          this.bar.open('Enter Name ', 'Close', {
            duration: 4000,
          });
          return;
        }
        if (this.password.length <= 5) {
          this.bar.open('password length must be greater than 5', 'Close', {
            duration: 4000,
          });
          return;
        }
        else if (this.email.includes("@") && this.email.includes(".com")) {

          if (res === 'ok') {
            this.name = '';
            this.email = '';
            this.password = '';
            this.bar.open('Registered Succesfully', 'Close', {
              duration: 3000,
            });
            // this.router.navigate(['loginComp'])

          } else {
            this.bar.open('Something went wrong please try again', 'Close', {
              duration: 3000,
            });
          }
        } else {
          this.bar.open('Enter Valid Email', 'Close', {
            duration: 4000,
          });

        }

      });
  }

  ngOnInit(): void { }
}
