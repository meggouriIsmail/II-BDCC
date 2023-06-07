import { Component } from '@angular/core';
import { Product } from '../models/product.model';

@Component({
  selector: 'app-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.css']
})
export class NewProductComponent {

  public product: Product={
    id: 0,
    name: '',
    price: 0,
    checked: false
  };

  constructor() {}

}
