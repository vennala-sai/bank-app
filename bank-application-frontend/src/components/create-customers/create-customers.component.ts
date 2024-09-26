import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Customer } from '../../model/customer';
import { ViewCustomerService } from '../../services/view-customer.service';
import { Router } from '@angular/router';
import { Address } from '../../model/address';
import { Account } from '../../model/account';

@Component({
  selector: 'app-create-customers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './create-customers.component.html',
  styleUrl: './create-customers.component.css'
})
export class CreateCustomersComponent {
  alertMessageChecking: string | null = null;
  alertMessageSavings: string | null = null;
  accountType: string = "";
  checkingAccount: Account = {
    balance: 0,
    type: 'CheckingAccount',
    nextCheckNumber: 0

  };
  savingsAccount: Account = {
    balance: 0,
    type: 'SavingsAccount',
    interestRate: 0
  };
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

  constructor(private service: ViewCustomerService, private router: Router) {}

  addAccount() {
    if (this.accountType == 'SavingsAccount'){
      this.customer.accounts.push(this.savingsAccount);
      this.alertMessageSavings = "Savings Account has been created!";
      this.savingsAccount = {
        balance: 0,
        type: 'SavingsAccount',
        interestRate: 0
      };
      setTimeout(() => {
        this.alertMessageSavings = null;
      }, 3000);
      
    }
    else if (this.accountType == 'CheckingAccount'){
      this.customer.accounts.push(this.checkingAccount);
      this.alertMessageChecking = "Checking Account has been created!";
      this.checkingAccount = {
        balance: 0,
        type: 'CheckingAccount',
        nextCheckNumber: 0
        
      };
      setTimeout(() => {
        this.alertMessageChecking = null;
      }, 3000);
    } 
    
    this.accountType = '';
  }

  createCustomer(){
    this.service.createCustomer(this.customer).subscribe(response=>{
      alert("The customer has been created and the id is: " + response.customerId);
      this.router.navigateByUrl('/view-customers');
    })
  }
  backToCustomers(){
    this.router.navigateByUrl('/view-customers');
  }
}