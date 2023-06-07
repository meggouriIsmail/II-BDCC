import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../models/product.model';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Array<Product> = [];

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.getProducts();
  }


  private getProducts() {
    this.productService.getProducts()
      .subscribe({
        next: value => {
          console.log(value);
          this.products = value;
        },
        error(err) {
          console.error(err);
        },
      });
  }

  handleCheck(product: Product) {
    this.productService.checkProduct(product)
      .subscribe({
        next: res => {
          console.log(res)
          product.checked = !product.checked;
        },
        error: err => console.error(err)
      })
  }

  handleDelete(product: Product) {
    if (confirm("Are you sure!"))
    this.productService.deleteProduct(product.id)
      .subscribe({
        next: res => {
          console.log(res);
          this.products.splice(this.products.indexOf(product),1)
        },
        error: err => console.error(err)
      })
  }
}
