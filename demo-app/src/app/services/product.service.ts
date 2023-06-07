import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  public getProducts():Observable<Array<Product>>{
    return this.http.get<Array<Product>>("http://localhost:9090/products");
  }

  public checkProduct(product:Product):Observable<Product>{
    return this.http.patch<Product>(`http://localhost:9090/products/${product.id}`, {checked:!product.checked});
  }

  public saveProduct(product:Product):Observable<Product>{
    return this.http.post<Product>(`http://localhost:9090/products`, product);
  }

  public deleteProduct(productId:number){
    return this.http.delete<any>(`http://localhost:9090/products/${productId}`);
  }

  public searchProducts(keyword:string):Observable<Array<Product>>{
    return this.http.get<Array<Product>>(`http://localhost:9090/products?name_like=${keyword}`);
  }
}
