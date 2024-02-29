import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  public products : any;

  constructor(private http : HttpClient) {
  }

  ngOnInit(): void {
    this.http.get("localhost:8087/api/products").subscribe({
      next : data => {
          this.products = data;
      },
      error : err => {console.error(err);
      }
    })
  }

}
