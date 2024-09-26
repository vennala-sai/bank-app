import { Component } from '@angular/core';
import { Customer } from '../../model/customer';
import { ViewCustomerService } from '../../services/view-customer.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-accounts',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './view-accounts.component.html',
  styleUrl: './view-accounts.component.css'
})
export class ViewAccountsComponent {
  customer: Customer = {
    name: '',
    customerType: '',
    address: {
      streetNumber: '',
      city: '',
      province: '',
      postalCode: ''
    },
    accounts: []
  };
  customerId: string = '';
  constructor(private route: ActivatedRoute, private service: ViewCustomerService, private router: Router) {
    this.route.params.subscribe(params => {
      this.customerId = params['id'];
      console.log('The customerId is: ' + this.customerId);
      this.service.getCustomer(this.customerId).subscribe(response => this.customer = response);
    });
  }

  backToCustomers(){
    this.router.navigateByUrl('/view-customers');
  }
}
