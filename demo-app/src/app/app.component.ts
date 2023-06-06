import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  [x: string]: any;

  actions: Array<any> = [
    { title: "Home", route: "/home", icon: "house" },
    { title: "Products", route: "/products", icon: "plus-circle" },
    { title: "New Product", route: "/new", icon: "cart" }
  ];

  currentAction: any;

  setCurrentAction(action: any) {
    this.currentAction = action;
  }

}
