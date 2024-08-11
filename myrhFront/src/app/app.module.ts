import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { OffersComponent } from './offers/offers.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { FooterComponent } from './footer/footer.component';
import { CompanyRegisterComponent } from './company-register/company-register.component';
import { HttpClientModule } from '@angular/common/http';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { AddOfferFormComponent } from './add-offer-form/add-offer-form.component';
import { OwnOffersComponent } from './own-offers/own-offers.component';
import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    OffersComponent,
    NavBarComponent,
    FooterComponent,
    CompanyRegisterComponent,
    CompanyProfileComponent,
    AddOfferFormComponent,
    OwnOffersComponent,
    AgentDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
