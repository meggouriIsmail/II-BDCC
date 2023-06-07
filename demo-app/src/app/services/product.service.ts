import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http:HttpClient) { }

  public getProducts():Observable<Array<any>>{
    return this.http.get<Array<any>>("http://localhost:9090/products")
  }

  public checkProduct(product:any):Observable<any>{
    return this.http.patch(`http://localhost:9090/products/${product.id}`, {checked:!product.checked})
  }
}
