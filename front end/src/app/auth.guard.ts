import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private auth: AuthService){}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const expectedRoleArray = route.data.expectedRole;
    const roleDetails = JSON.parse(localStorage.getItem('user'));
    let role;
    for(const index in expectedRoleArray){
      if(roleDetails && roleDetails.role === expectedRoleArray[index] ){
        role= expectedRoleArray[index];
      }
    }

    if (role === roleDetails.role){
      return true;
    } else{
      return false;
    }
  } 
  
}
