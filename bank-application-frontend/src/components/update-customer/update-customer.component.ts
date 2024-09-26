import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../../model/customer';
import { ViewCustomerService } from '../../services/view-customer.service';

@Component({
  selector: 'app-update-customer',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './update-customer.component.html',
  styleUrl: './update-customer.component.css'
})
export class UpdateCustomerComponent {
  deleteIcon: boolean | null = null;
  id: string = '';
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

  constructor(private route: ActivatedRoute, private service: ViewCustomerService, private router: Router) {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      console.log('The id is: ' + this.id);
      service.getCustomer(this.id).subscribe(response => this.customer = response);
    });
  }

  updateCustomer() {
    this.service.updateCustomer(this.customer).subscribe(response => {
      alert("The customer with id: " + this.id + " has been updated!");
      this.router.navigateByUrl('/view-customers');
    });
  }
  deleteCustomer() {
    this.service.deleteCustomer(this.id).subscribe(response => {
      alert("The customer with id: " + this.id + " has been deleted!");
      this.router.navigateByUrl('/view-customers');
    });
  }
  backToCustomers(){
    this.router.navigateByUrl('/view-customers');
  }

}
