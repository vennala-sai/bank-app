import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../model/customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewCustomerService {
  url: string = "http://localhost:8083/api/v1/customers";
  constructor(private http: HttpClient) { }
  getCustomers(): Observable<Customer[]>{
    return this.http.get<Customer[]>(this.url + "/allcustomers");
  }
  deleteCustomer(id: string) {
    return this.http.delete(this.url+"/delete/"+id);
  }
  createCustomer(newcustomer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.url + "/create", newcustomer);
  }
  getCustomer(id: string): Observable<Customer> {
    return this.http.get<Customer>(this.url + "/"+ id);
  }
  updateCustomer(updatedcustomer: Customer) {
    return this.http.put(this.url+"/update/"+updatedcustomer.customerId , updatedcustomer);
  }
}
