import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent {
  accounts : any

  constructor(private httpClient : HttpClient) {

  }

  ngOnInit(): void {
    this.httpClient.get("http://localhost:8888/ACCOUNT-SERVICE/accounts").subscribe({
      next : data => {
          this.accounts = data;
      },
      error(err) {
          console.error(err);
      },
    })
  }
}
