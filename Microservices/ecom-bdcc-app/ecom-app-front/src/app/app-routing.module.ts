import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ProductsComponent} from "./ui/products/products.component";
import {CustomersComponent} from "./ui/customers/customers.component";
import {CommonModule} from "@angular/common";


const routes: Routes = [
  {path: "products", component: ProductsComponent},
  {path: "customers", component: CustomersComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    CommonModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
