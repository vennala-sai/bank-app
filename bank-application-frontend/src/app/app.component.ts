import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { CreateCustomersComponent } from '../components/create-customers/create-customers.component';
import { UpdateCustomerComponent } from '../components/update-customer/update-customer.component';
import { ViewCustomersComponent } from '../components/view-customers/view-customers.component';
import { ViewAccountsComponent } from '../components/view-accounts/view-accounts.component';
import { HomeComponent } from '../components/home/home.component';
import { NotFoundComponent } from '../components/not-found/not-found.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, NavbarComponent, CreateCustomersComponent, UpdateCustomerComponent, ViewCustomersComponent, ViewAccountsComponent, HomeComponent, NotFoundComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'BankApplicationFE';
}
