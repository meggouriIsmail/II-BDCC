import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterOutlet} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';
import { ProductsComponent } from './ui/products/products.component';
import { CustomersComponent } from './ui/customers/customers.component';
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    CustomersComponent
  ],
    imports: [
        BrowserModule,
        RouterOutlet,
        AppRoutingModule,
        HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
