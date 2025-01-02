import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../services/customer.service';

declare var bootstrap: any;

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {
  customers: any[] = [];
  newCustomer: any = { id: 0, firstName: '', lastName: '', email: '' };
  currentCustomer: any = { id: 0, firstName: '', lastName: '', email: '' }; // Customer being edited
  errorMessage: string = '';

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadCustomers();
  }

  // Load customers from the API
  loadCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load customers. Please try again later.';
      },
    });
  }

  addCustomer(): void {
    this.customerService.createCustomer(this.newCustomer).subscribe({
      next: () => {
        this.loadCustomers();
        this.closeModal('addCustomerModal');
      },
      error: (err) => {
        this.errorMessage = 'Failed to add customer. Please try again later.';
      },
    });
  }

  openAddModal(): void {
    const modalElement = document.getElementById('addCustomerModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  openEditModal(customer: any): void {
    this.currentCustomer = { ...customer };
    const modalElement = document.getElementById('updateCustomerModal');
    if (modalElement) {
      const modal = new bootstrap.Modal(modalElement);
      modal.show();
    }
  }

  updateCustomer(): void {
    this.customerService.updateCustomer(this.currentCustomer).subscribe({
      next: () => {
        this.loadCustomers();
        this.closeModal('updateCustomerModal');
      },
      error: (err) => {
        this.errorMessage = 'Failed to update customer. Please try again later. ' + err;
      },
    });
  }

  deleteCustomer(id: number): void {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.customerService.deleteCustomer(id).subscribe({
        next: () => {
          this.loadCustomers();
        },
        error: (err) => {
          this.errorMessage = 'Failed to delete customer. Please try again later. ' + err;
        },
      });
    }
  }

  closeModal(modalId: string): void {
    const modalElement = document.getElementById(modalId);
    if (modalElement) {
      const modal = bootstrap.Modal.getInstance(modalElement);
      modal.hide();
    }
  }
}
