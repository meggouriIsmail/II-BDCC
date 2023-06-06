import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Array<any> = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:9000/products").subscribe({
      next: value => {
        console.log(value);
        this.products = value
      },
      error(err) {
        console.error(err);
      },
    })
  }


  handleCheck(product: any) {
    product.checked = !product.checked;
    this.http.put(`http://localhost:9000/products/${product.id}`, product)
      .subscribe({
        next: res => console.log(res),
        error: err => console.error(err)
      })
  }
}
