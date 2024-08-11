import { Offer } from './../module/offer.module';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup,FormBuilder, NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient} from '@angular/common/http';
import { OffersService } from '../service/offers.service';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css'],
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
export class OffersComponent implements OnInit {
  
  fakeArray!:Array<Offer> ;
  offers:Array<Offer> = new Array();
  currentPage!: number;
  numPages!:Array<number>;
  
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
    this.offersService.getOffers().subscribe({
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
      this.numPages = Array(~~(this.fakeArray.length/5)+1).fill(~~(this.fakeArray.length/5)+1).map((x,i)=>i);
      this.offers= this.fakeArray.slice(4*page,4*page+4)
    }
  }

  filter(f:NgForm){
    console.log(f.value)
    this.offers = this.fakeArray.filter(item=>{
      return item.ville.toLocaleLowerCase().includes(f.value.city.toLocaleLowerCase()) && item.title.toLocaleLowerCase().includes(f.value.job.toLocaleLowerCase())
    })
  }

}

