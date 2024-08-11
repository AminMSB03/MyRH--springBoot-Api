import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { Authentication } from '../service/authentication.service.service';

@Injectable({
  providedIn: 'root'
})
export class AutheticationGuard implements CanActivate {

  constructor(private authService : Authentication){

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.authService.authenticated?.role=="COMPANY"){
      return true
    }
  }
  
}
