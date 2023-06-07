import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Array<any> = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.productService.getProducts().subscribe({
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
    this.productService.checkProduct(product)
    .subscribe({
      next: res => {
          console.log(res)
          product.checked = !product.checked;
        },
        error: err => console.error(err)
      })
  }
}
