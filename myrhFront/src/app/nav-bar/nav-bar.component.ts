import { data } from 'autoprefixer';
import { Component, OnInit } from '@angular/core';
import { Authentication } from '../service/authentication.service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(public auth : Authentication) {
    this.auth.isUserLoggedIn.subscribe(loggedIn => {
      this.showLoggin = loggedIn;
    });
  }

  showLoggin:boolean = true
  role!:string 

  ngOnInit(): void {
    
    // this.auth.role.asObservable().subscribe({
    //   next: value=>{
    //     this.role = value
    //   }
    // })
  }

  logout(){
    this.auth.logout()
  }
  
}
