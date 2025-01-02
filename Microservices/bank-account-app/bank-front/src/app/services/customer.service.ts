import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root', // Automatically registers this service at the root level
})
export class CustomerService {
  private baseUrl = 'http://localhost:8888/CUSTOMER-SERVICE/customers';

  constructor(private httpClient: HttpClient) {}

  getCustomers(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }

  getCustomerById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/${id}`);
  }

  createCustomer(customer: any): Observable<any> {
    return this.httpClient.post(`${this.baseUrl}`, customer);
  }

  updateCustomer(customer: any): Observable<any> {
    return this.httpClient.put<any>(`${this.baseUrl}/${customer.id}`, customer);
  }

  deleteCustomer(id: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
