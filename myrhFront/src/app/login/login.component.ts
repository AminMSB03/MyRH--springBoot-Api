import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup,FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Authentication } from '../service/authentication.service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  companyFormGroup!: FormGroup;

  errorMessage!: string;


  constructor(private authenicationService :Authentication,private router : Router) { }

  ngOnInit(): void {

  }


  handleLogin(){
    console.log(this.companyFormGroup.value)
  }

  getLoginData(f: NgForm){
    let formdata = new FormData();
    formdata.append("email", f.value.email);
    formdata.append("password", f.value.password);

    this.authenicationService.login(formdata).subscribe({
      next : (value)=>{
        this.authenicationService.authenticateUser(value)
        this.authenicationService.changeUserLoggedIn(true)
        if(value.role=="COMPANY"){
          this.router.navigateByUrl("/profile/offers")
        }else{
          this.router.navigateByUrl("/agent/dashboard")
        }
      },
      error : (err) =>{
        this.errorMessage = "Bad credentials"
      },
    })

  }

}
