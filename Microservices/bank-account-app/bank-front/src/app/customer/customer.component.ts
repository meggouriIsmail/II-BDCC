import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit{
  customers : any

  constructor(private httpClient : HttpClient) {

  }

  ngOnInit(): void {
    this.httpClient.get("http://localhost:8888/CUSTOMER-SERVICE/customers").subscribe({
      next : data => {
          this.customers = data;
      },
      error(err) {
          console.error(err);
      },
    })
  }


}
