import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-offers',
  templateUrl: './offers.component.html',
  styleUrls: ['./offers.component.css']
})
export class OffersComponent implements OnInit {
  
  fakeArray;
  
  constructor() {
    this.fakeArray = new Array(12)
  }

  ngOnInit(): void {
  }

}

