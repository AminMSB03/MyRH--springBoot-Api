import { AgentDashboardComponent } from './agent-dashboard/agent-dashboard.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { OffersComponent } from './offers/offers.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import { CompanyRegisterComponent } from './company-register/company-register.component';
import { CompanyGuard } from './guards/company.guard';
import { OwnOffersComponent } from './own-offers/own-offers.component';
import { AddOfferFormComponent } from './add-offer-form/add-offer-form.component';
import { AgentGuard } from './guards/agent.guard';

const routes: Routes = [
  {path:"",component: OffersComponent},
  {path:"login",component: LoginComponent},
  {path:"offers",component: OffersComponent},
  {path:"register",component: CompanyRegisterComponent},
  {path:"agent/dashboard",component: AgentDashboardComponent, canActivate: [AgentGuard]},
  {path:"profile",component: CompanyProfileComponent, canActivate: [CompanyGuard]
  ,children:[{path:"offers",component:OwnOffersComponent},{path:"add_offer",component:AddOfferFormComponent}]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
