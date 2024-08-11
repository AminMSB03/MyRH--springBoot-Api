import { Offer } from './../module/offer.module';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { OffersService } from '../service/offers.service';


@Component({
  selector: 'app-own-offers',
  templateUrl: './own-offers.component.html',
  styleUrls: ['./own-offers.component.css'],
  animations: [
    trigger('openClose', [
      state('open', style({
        height: '150px',
        opacity: 1,
      })),
      state('closed', style({
        height: '0',
        opacity: 0,
      })),
      transition('open => closed', [
        animate('0.3s')
      ]),
      transition('closed => open', [
        animate('0.3s')
      ]),
    ]),
  ],
})
export class OwnOffersComponent implements OnInit {

  fakeArray!:Array<Offer> ;
  offers!:Array<Offer>;
  currentPage!: number;
  numPages!:number;

  constructor(private http : HttpClient,private offersService:OffersService,private router:Router) {
  }
  
  ngOnInit(): void {
    this.getOffer()
  }

  toggle(idGenven: Number) {
    this.offers.forEach((element,i) => {
      if(i == idGenven){
        element.isOpen = !element.isOpen
      }
    });
  }

  getOffer(){
    this.offersService.getCompanyOffers().subscribe({
      next: (data:any)=>{
        this.offersService.offers = data
        this.fakeArray = this.offersService.offers
        this.pagination(0)
      },
      error:()=>{
        localStorage.clear()
        this.router.navigateByUrl("/login")
      }
    })
  }

  pagination(page:number){
    if(page>=0){
      this.currentPage = page
      this.numPages = ~~(this.fakeArray.length/5)+1
      this.offers= this.fakeArray.slice(2*page,2*page+2)
    }
  }

  

}
