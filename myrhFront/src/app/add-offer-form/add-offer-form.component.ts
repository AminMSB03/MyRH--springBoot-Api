import { NgForm } from '@angular/forms';
import { OffersService } from './../service/offers.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-offer-form',
  templateUrl: './add-offer-form.component.html',
  styleUrls: ['./add-offer-form.component.css']
})
export class AddOfferFormComponent implements OnInit {

  constructor(private offerservice : OffersService,private router:Router) { }
  buttonValue:string = "Save";

  ngOnInit(): void {
  }

  addOffer(f: NgForm){
    this.offerservice.addOffer(f.value).subscribe({
      next:(value:any)=>{
        console.log(value)
        this.buttonValue = "Saved!"
      },
      error:()=>{
        localStorage.clear()
        this.router.navigateByUrl("/login")
      }
    })
  }

}
