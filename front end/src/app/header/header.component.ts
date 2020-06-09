import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor( private router: Router) { }

  
  name;
  isCustomer() {
    const customerDetails = JSON.parse(localStorage.getItem('user'));
    if (customerDetails && customerDetails.userType === 'customer') {
      this.name =  customerDetails.name;
      return true;
    } else {
      return false;
    }
  }

  isAdmin() {
    const adminDetails = JSON.parse(localStorage.getItem('user'));
    if (adminDetails && adminDetails.userType === 'admin') {
      this.name =  adminDetails.name;
      return true;
    } else {
      return false;
    }
  }

  isMerchant() {
    const merchantDetails = JSON.parse(localStorage.getItem('user'));
    if (merchantDetails && merchantDetails.userType === 'direct') {
      this.name =  merchantDetails.name;
      return true;
    } else {
      return false;
    }
  }

  isLoggedIn() {
    const loginData = JSON.parse(localStorage.getItem('user'));
    if (loginData) {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    localStorage.removeItem('user');
    this.router.navigateByUrl('/');
  }

  ngOnInit() {
  }

}
