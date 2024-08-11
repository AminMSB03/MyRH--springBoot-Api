import { CompanyService } from './../service/company.service';
import { Component, OnInit } from '@angular/core';
import { Company } from '../module/company.module';
import { Router } from '@angular/router';

@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {

  constructor(private companyService:CompanyService,private router:Router) { }

  currentcompany!:Company;

  ngOnInit(): void {
    this.companyService.getCompanyInfos().subscribe({
      next:(value:any)=>{
        console.log(value)
        this.currentcompany = value
        console.log(this.currentcompany)
      },
      error:()=>{
        localStorage.clear()
        this.router.navigateByUrl("/login")
      }
    })
  }



}
