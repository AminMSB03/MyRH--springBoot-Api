import { Component, OnInit } from '@angular/core';
import { FormGroup,FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  companyFormGroup!: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.companyFormGroup = this.fb.group({
      email: this.fb.control(""),
      password: this.fb.control("")
    })
  }

  handleLogin(){
    console.log(this.companyFormGroup.value)
  }

  

}
