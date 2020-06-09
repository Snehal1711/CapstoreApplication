import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { CustomerRegisterComponent } from './customer-register/customer-register.component';
import { MerchantRegisterComponent } from './merchant-register/merchant-register.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AuthGuard } from './auth.guard';
import { ThirdpartyMerchantComponent } from './thirdparty-merchant/thirdparty-merchant.component';


const routes: Routes = [
  {path : '', component : HomeComponent},
  {path : 'login', component : LoginComponent},
  {path : 'customer-register' , component : CustomerRegisterComponent },
  {path : 'merchant-register', component : MerchantRegisterComponent},
  {path : 'change-password' , component : ChangePasswordComponent, canActivate : [AuthGuard],
  data: { expectedRole : ['customer', 'direct', 'admin']}},
  {path : 'forgot-password' , component : ForgotPasswordComponent},
  {path : 'third-party-merchant', component : ThirdpartyMerchantComponent , canActivate : [AuthGuard], 
  data: { expectedRole : ['admin']} }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
