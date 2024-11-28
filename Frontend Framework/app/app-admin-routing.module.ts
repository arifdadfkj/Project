import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupComponent } from './signup/signup.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';

const routes: Routes = [
  {
    path:'signup', component:SignupComponent,
  },
  {
    path:'forgotten-ps',component:ForgottenPasswordComponent,
  },
  {
    path:'change-ps',component:ChangePasswordComponent,
  },
  {
    path:'login',component:LoginComponent,
  },
  {
    path:'logout',component:LogoutComponent,
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppAdminRoutingModule { }
