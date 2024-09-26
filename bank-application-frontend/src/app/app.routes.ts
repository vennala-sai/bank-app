import { Routes } from '@angular/router';
import { CreateCustomersComponent } from '../components/create-customers/create-customers.component';
import { ViewCustomersComponent } from '../components/view-customers/view-customers.component';
import { UpdateCustomerComponent } from '../components/update-customer/update-customer.component';
import { ViewAccountsComponent } from '../components/view-accounts/view-accounts.component';
import { HomeComponent } from '../components/home/home.component';
import { NotFoundComponent } from '../components/not-found/not-found.component';

export const routes: Routes = [
    {path: "create-customers", component: CreateCustomersComponent},
    {path: "view-customers", component: ViewCustomersComponent},
    {path:'update-customer/:id',component: UpdateCustomerComponent},
    {path:'view-accounts/:id',component: ViewAccountsComponent},
    {path:'',component: HomeComponent},
    {path:'**', component: NotFoundComponent}
]; 
