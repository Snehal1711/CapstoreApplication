import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-register',
  templateUrl: './customer-register.component.html',
  styleUrls: ['./customer-register.component.css']
})
export class CustomerRegisterComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }

  message: string;
  statusCode ;
  customerRegister(registerForm: NgForm) {
    console.log(registerForm.value);
    this.authService.customerRegister(registerForm.value).subscribe(response => {
      console.log(response);
      this.statusCode = response.statusCode;
      if (response.statusCode === 200) {
        this.message = response.description;
        this.router.navigateByUrl('/login');
      } else {
        this.message = response.description;
      }
      registerForm.reset();
    });
  }
  ngOnInit() {
  }

}
