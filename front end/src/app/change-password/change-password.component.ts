import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private authservice: AuthService) { }
  message: string;
  statusCode ;
  changePassword(changePasswordForm:NgForm ){
    console.log(changePasswordForm.value);
    this.authservice.changePassword(changePasswordForm.value).subscribe(response => {
      console.log(response);
      this.statusCode = response.statusCode;
      this.message = response.description;
      changePasswordForm.reset();
    });
  }
  ngOnInit() {
  }

}
