import { CompanyService } from './../service/company.service';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Company } from '../module/company.module';

@Component({
  selector: 'app-company-register',
  templateUrl: './company-register.component.html',
  styleUrls: ['./company-register.component.css']
})
export class CompanyRegisterComponent implements OnInit {

  file!: File;
  comapany!: Company;
  buttonValue:string = "Create an account"


  constructor(private http:HttpClient, private companyService :CompanyService) { }
  ngOnInit(): void {
  }

  onFileChanged(event:any) {
    this.file = event.target.files[0];
    console.log(this.file.name)
  }

  register(f: NgForm){
    
    const formData = new FormData();
    formData.append('file', this.file);
    formData.append("email", f.value.email);
    formData.append("name", f.value.name);
    formData.append("password", f.value.password);
    formData.append("phoneNumber", f.value.phoneNumber);
    formData.append("image", this.file.name);
    this.companyService.register(formData).subscribe({
      next:(value)=>{
        this.buttonValue = "Account created!"
        console.log("helloooo")
      }
    })
  }


  
}
