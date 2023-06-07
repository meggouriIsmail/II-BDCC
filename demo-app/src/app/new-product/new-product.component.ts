import {Component, OnInit} from '@angular/core';
import { Product } from '../models/product.model';
import {FormBuilder, FormGroup} from '@angular/forms'
import {ProductService} from "../services/product.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent implements OnInit{
  public product!: FormGroup;

  constructor(private fb:FormBuilder,
              private productService:ProductService,
              private router:Router) {}
  ngOnInit(): void {
    this.product=this.fb.group({
      name: this.fb.control(''),
      price: this.fb.control(''),
      checked:this.fb.control(false)
    })
  }

  saveProduct() {
    this.productService.saveProduct(this.product.value)
      .subscribe({
        next :res => {
          console.log(res);
          this.router.navigateByUrl("/products")
        },
        error :err => {console.error(err)}
      })
  }
}
