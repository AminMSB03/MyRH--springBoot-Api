import { Injectable } from '@angular/core';
import { Offer } from './../module/offer.module';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { AuthenticatedMoule } from '../module/authenticated.module';

@Injectable({
  providedIn: 'root'
})
export class OffersService {

  constructor(private http : HttpClient) { }
  
  offers: Offer[] = new Array();
  
  getOffers(){
    return this.http.get("http://localhost:8081/api/accepted_offers")
  }

  addOffer(offer: Offer){
    const user = localStorage.getItem("authUser")
    let auth:AuthenticatedMoule = user!=null? JSON.parse(user):""
    let auth_token = auth.access_token

    return this.http.post<Offer>('http://localhost:8081/api/offers', offer, {headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${auth_token}`
    })})
  }

  getCompanyOffers(){
    const user = localStorage.getItem("authUser")
    let auth:AuthenticatedMoule = user!=null? JSON.parse(user):""
    let auth_token = auth.access_token

    return this.http.get('http://localhost:8081/api/offers', {headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${auth_token}`
    })})
  }


  getPendingOffers(){
    const user = localStorage.getItem("authUser")
    let auth:AuthenticatedMoule = user!=null? JSON.parse(user):""
    let auth_token = auth.access_token

    return this.http.get('http://localhost:8081/api/pending_offers', {headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${auth_token}`
    })})
  }

  updateOffers(action:string,id:number){
    const user = localStorage.getItem("authUser")
    let auth:AuthenticatedMoule = user!=null? JSON.parse(user):""
    let auth_token = auth.access_token

    let data = {
      "action":action,
      "id":id
    }

    return this.http.put('http://localhost:8081/api/offers',JSON.stringify(data),{headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${auth_token}`
    })})
  }

}
