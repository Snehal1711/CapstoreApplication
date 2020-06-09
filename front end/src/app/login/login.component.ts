import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService: AuthService, private router: Router) { }
   message: string;

  login(loginForm: NgForm) {
  console.log(loginForm.value);
  this.authService.login(loginForm.value).subscribe(response => {
      console.log(response);
      if (response.statusCode == 222) {
        localStorage.setItem('user', JSON.stringify(response.loginBean));
        this.router.navigateByUrl('/');
      } else {
        this.message = response.description;
      }
    });
  }
  ngOnInit() {
  }

}
