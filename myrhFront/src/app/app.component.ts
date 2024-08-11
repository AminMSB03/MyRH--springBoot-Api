import { AuthenticatedMoule } from './module/authenticated.module';
import { Component, OnInit } from '@angular/core';
import { Authentication } from './service/authentication.service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'myrhFront';

  constructor(private authService : Authentication){

  }


  ngOnInit(): void {

    let user = localStorage.getItem("authUser")
    if(user!=null){
      let auth:AuthenticatedMoule = JSON.parse(user)
      this.authService.authenticated = auth
      this.authService.changeUserLoggedIn(true)
      // this.authService.raiseroleInEvent(auth.role)
    }
  }

}
