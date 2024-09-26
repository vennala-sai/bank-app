import { Component } from '@angular/core';
import { ViewCustomerService } from '../../services/view-customer.service';
// import { response, Router } from 'express';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Customer } from '../../model/customer';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-view-customers',
  standalone: true,
  imports: [CommonModule, RouterModule, FormsModule],
  templateUrl: './view-customers.component.html',
  styleUrl: './view-customers.component.css'
})
export class ViewCustomersComponent {
  query: string = '';
  isSearch: boolean = false;
  customers: Customer[] = [];
  searchCustomers: Customer[] = [];
  service: ViewCustomerService;
  constructor(service: ViewCustomerService, private router: Router){
    service.getCustomers().subscribe(response => {
      this.customers = response;
    })
    this.service = service; //need it to implement search feature
  }
  onSearch(){
   this.searchCustomers = [];
   for(var i = 0; i < this.customers.length; i++){
    if (this.customers[i].customerId?.toString().includes(this.query)){
      this.searchCustomers.push(this.customers[i]); //filters by search
      this.isSearch = true;
    }
   }
   if (this.query === ''){
    this.isSearch = false;
    this.searchCustomers = this.customers; //should not be used but safety for potential edge case
   }
  }
  viewAccounts(customer: Customer){
    this.router.navigate(['/view-accounts', customer.customerId]);
  }
  backToCustomers(){
    this.router.navigateByUrl('');
  }
}
