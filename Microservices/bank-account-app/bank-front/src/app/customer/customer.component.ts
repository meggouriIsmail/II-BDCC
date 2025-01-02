import { Component, OnInit } from '@angular/core';
import {CustomerService} from "../services/customer.service";

declare var bootstrap: any;

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customers: any = [];
  errorMessage: string | null = null;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  newCustomer: any = { firstName: '', lastName: '', email: '' };

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        this.errorMessage = "Failed to load customers. Please try again later.";
        console.error(err);
      },
    });
  }

  addCustomer(): void {
    this.customerService.createCustomer(this.newCustomer).subscribe({
      next: () => {
        this.newCustomer = { firstName: '', lastName: '', email: '' };
        const modal = new bootstrap.Modal(document.getElementById('addAccountModal'));
        modal.hide();
      },
      error: (err) => {
        console.error('Error adding customer:', err);
      },
    });
  }

  deleteCustomer(id: number): void {
    this.customerService.deleteCustomer(id).subscribe({
      next: () => {
        this.customers = this.customers.filter((customer: any) => customer.id !== id);
      },
      error: (err) => {
        this.errorMessage = `Failed to delete customer with ID ${id}.`;
        console.error(err);
      },
    });
  }
}
