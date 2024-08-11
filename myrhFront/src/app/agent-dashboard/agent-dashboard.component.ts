import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Offer } from '../module/offer.module';
import { OffersService } from '../service/offers.service';

@Component({
  selector: 'app-agent-dashboard',
  templateUrl: './agent-dashboard.component.html',
  styleUrls: ['./agent-dashboard.component.css']
})
export class AgentDashboardComponent implements OnInit {

  constructor(private offerService: OffersService,private router: Router) { }


  offers:Offer[] = new Array();


  ngOnInit(): void {
    this.getOffers()
  }


  getOffers(){
    this.offerService.getPendingOffers().subscribe({
      next:(value:any)=>{
        this.offers = value
        console.log(this.offers)
      },
      error:()=>{
        localStorage.clear()
        this.router.navigateByUrl("/login")
      }
    })
  }

  updateOffer(action:string, id:number){
    this.offers = this.offers.filter(item=>item.id!=id)
    this.offerService.updateOffers(action,id).subscribe({
      next:(value)=>{
        console.log(value)
      },
      error:(err)=>{
        console.log("errrrrr")
        console.log(err)
        localStorage.clear()
        this.router.navigateByUrl("/login")
      }
    })
  }

}
