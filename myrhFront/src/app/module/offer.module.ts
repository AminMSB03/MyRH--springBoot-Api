import { Company } from "./company.module"

export interface Offer{
  id:number,
  title: string,
  description:string,
  createdAT: string,
  profileRequired:string,
  ville:string,
  educationLevel:string,
  salary:string,
  isOpen:boolean
  company: Company
}