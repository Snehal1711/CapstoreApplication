import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-merchant-register',
  templateUrl: './merchant-register.component.html',
  styleUrls: ['./merchant-register.component.css']
})
export class MerchantRegisterComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }
message: string;
statusCode ;
merchantRegister(registerForm: NgForm) {
  console.log(registerForm.value);
  this.authService.merchantRegister(registerForm.value).subscribe(response => {
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
