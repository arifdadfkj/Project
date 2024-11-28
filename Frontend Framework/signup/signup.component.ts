import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { SignupService } from './signup.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  person:FormGroup;
  strongcheckMessage : String = "";
  constructor(formBuilder : FormBuilder,private service: SignupService){
    this.person = formBuilder.group({
     firstName : new FormControl('',[Validators.required,Validators.minLength(3),Validators.maxLength(25),Validators.pattern('[a-z]+$')]),
     lastName : new FormControl('',[Validators.required,Validators.minLength(2),Validators.maxLength(15),Validators.pattern('[a-z]+$')]),
     username : new FormControl('',[Validators.required,Validators.minLength(3),Validators.email]),
     password : new FormControl('',Validators.required),
     confirmPassword : new FormControl('',Validators.required),
    });
  }
  get firstName(){
    return this.person.get('firstName');
  }
  get lastName(){
    return this.person.get('lastName');
  }
  get username(){
    return this.person.get('username');
  }
  get password(){
    return this.person.get('password');
  }
  get confirmPassword(){
    return this.person.get('confirmPassword');
  }
  checkStrongPassword() : boolean{
    var password = this.person.value.password;
    if(password.search(/[a-z]/) == -1){
      this.strongcheckMessage = "password must contain atleast one lower case letter";
      return true;
    }
    if(password.search(/[A-Z]/) == -1){
      this.strongcheckMessage = "password must contain at atleast one uppercase letter";
      return true;
    }
    if(password.search(/[0-9]/) == -1){
      this.strongcheckMessage = "password must contain at atleast one digit ";
      return true;
    }
    if(password.indexOf('_') == -1 &&password.search(/\W/) == -1){
      this.strongcheckMessage = "password must contain at atleast one spl char";
      return true;
    }
    return false;
  }
  checkStrongConfirmPassword() : boolean{
    var password = this.person.value.password;
    var confirmPassword = this.person.value.confirmPassword;
    if(confirmPassword.search(/[a-z]/) == -1){
      this.strongcheckMessage = "confirmPassword must contain atleast one lower case letter";
      return true;
    }
    if(confirmPassword.search(/[A-Z]/) == -1){
      this.strongcheckMessage = "confirmPassword must contain at atleast one uppercase letter";
      return true;
    }
    if(confirmPassword.search(/[0-9]/) == -1){
      this.strongcheckMessage = "confirmPassword must contain at atleast one digit ";
      return true;
    }
    if(confirmPassword.indexOf('_') == -1 &&confirmPassword.search(/\W/) == -1){
      this.strongcheckMessage = "confirmPassword must contain at atleast one spl char";
      return true;
    }
    if(password != confirmPassword){
      this.strongcheckMessage = "Password and confirmPassword must be same";
      return true;
    }
    return false;
  }

  save(){
      if(this.person.invalid){
        this.person.markAllAsTouched();
        return;
      }
       this.person.removeControl('confirmPassword'); 
       console.log(this.person.value);
       this.service.save(this.person).subscribe(
        r1 => {
          alert("user registered successfully" + r1);
        }
       )
  }
}
