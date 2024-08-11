import { AuthenticatedMoule } from './../module/authenticated.module';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { Next } from 'react-bootstrap/esm/PageItem';
import { BehaviorSubject, Observable, of, Subject } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class Authentication {

  constructor(private http: HttpClient, private router : Router) { }

  authenticated: AuthenticatedMoule | undefined;
  private isLoggedIn = new BehaviorSubject<boolean>(false);

  get isUserLoggedIn() {
    return this.isLoggedIn.asObservable();
  }
  changeUserLoggedIn(data:boolean){
    this.isLoggedIn.next(data);
  }
  // role =  new BehaviorSubject("NONE");

  

  // raiseroleInEvent(data: string){
  //   this.role.next(data)
  // }


  login(formdata: FormData):Observable<AuthenticatedMoule>{
    return this.http.post<AuthenticatedMoule>("http://localhost:8081/login",formdata)
  }

  public authenticateUser(user : AuthenticatedMoule){
    this.authenticated = user
    localStorage.setItem("authUser",JSON.stringify(user))
  }

  public hasRole(role : string){
      return this.authenticated!.role = role
  }

  public logout(){
    this.authenticated = undefined;
    this.router.navigateByUrl("/login")
    localStorage.removeItem("authUser")
    this.changeUserLoggedIn(false)
    return of(true)
  }

  public isAuthenticated(){
    return this.authenticated != undefined;
  }

  public isAgent(){
    return this.authenticated?.role == "AGENT"
  }

  public isCompany(){
    return this.authenticated?.role == "COMPANY"
  }


}
