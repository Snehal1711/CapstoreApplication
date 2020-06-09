import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  constructor(private authService: AuthService) { }
  message: string;
  statusCode;
  forgotPassword(forgotPasswordForm: NgForm){
    console.log(forgotPasswordForm.value);
    if (forgotPasswordForm.value.password === forgotPasswordForm.value.confirmPassword) {
      this.authService.forgotPassword(forgotPasswordForm.value).subscribe(response => {
      console.log(response);
      this.message = response.description;
      this.statusCode = response.statusCode;
      });
    } else {
      this.statusCode = 401;
      this.message = 'Password and Confirm Password not matches';
    }
  }
  ngOnInit() {
  }

}
