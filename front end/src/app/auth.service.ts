import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  api = 'http://localhost:8080/';

  login(user): Observable<any> {
    return this.http.post(`${this.api}login`, user);
  }

  customerRegister(customer): Observable<any> {
    console.log(customer);
    return this.http.post(`${this.api}customerRegistration`, customer);
  }

  merchantRegister(merchant): Observable<any> {
    console.log(merchant);
    return this.http.post(`${this.api}merchantRegistration`, merchant);
  }

  changePassword(data): Observable<any> {
    const newDetails = {
      password : data.password,
      id : JSON.parse(localStorage.getItem('user')).id,
    };
    console.log('new Password' + data.newPassword);
    return this.http.post(`${this.api}changePassword?newPassword=${data.newPassword}`, newDetails);
  }

  forgotPassword(data): Observable<any> {
    return this.http.post(`${this.api}forgetPassword?email=${data.email}&newPassword=${data.password}`, data);
  }
}
