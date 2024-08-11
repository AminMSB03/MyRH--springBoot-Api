import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticatedMoule } from '../module/authenticated.module';
import { Company } from '../module/company.module';
import { Offer } from '../module/offer.module';


@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) { }
  offers!:Offer[];
  company!: Company;
  
  getCompanyInfos():Observable<any>{
    const user = localStorage.getItem("authUser")
    let auth:AuthenticatedMoule = user!=null? JSON.parse(user):""
    let auth_token = auth.access_token
    
    let myHeaders = new Headers();
    myHeaders.append("Authorization", "Bearer "+auth_token);

    return this.http.get("http://localhost:8081/api/companies",{headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${auth_token}`
    })})
  }
  
  register(formData : FormData){
    return this.http.post("http://localhost:8081/api/companies",formData)
  }

}
