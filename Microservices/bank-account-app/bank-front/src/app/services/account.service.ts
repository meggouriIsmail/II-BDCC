import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  private baseUrl = 'http://localhost:8888/ACCOUNT-SERVICE/accounts';

  constructor(private httpClient: HttpClient) {}

  getAccounts(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }

  getAccountById(id: string): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/${id}`);
  }

  createAccount(account: any): Observable<any> {
    return this.httpClient.post(this.baseUrl, account);
  }

  updateAccount(id: string, account: any): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/${id}`, account);
  }

  deleteAccount(id: string): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
