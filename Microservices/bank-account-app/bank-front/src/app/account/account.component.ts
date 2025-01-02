import { Component, OnInit } from '@angular/core';
import { AccountService } from '../services/account.service';
import { CustomerService } from '../services/customer.service';

declare var bootstrap: any;

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  accounts: any[] = [];
  customers: any[] = [];
  errorMessage: string | null = null;
  newAccount: any = { type: '', balance: 0, currency: '', customerId: '' };

  constructor(private accountService: AccountService,
              private customerService: CustomerService) {}

  ngOnInit(): void {
    this.loadAccounts();
    this.loadCustomers();
  }

  loadAccounts(): void {
    this.accountService.getAccounts().subscribe({
      next: (data) => {
        this.accounts = data;
      },
      error: (err) => {
        this.errorMessage = 'Failed to load accounts. Please try again later.';
        console.error(err);
      },
    });
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data;
      },
      error: (err) => {
        console.error('Error loading customers:', err);
      },
    });
  }
  addAccount(): void {
    this.accountService.createAccount(this.newAccount).subscribe({
      next: () => {
        this.loadAccounts();
        this.newAccount = { type: '', balance: 0, currency: '', customerId: '' };

        // Close the modal using Bootstrap Modal API
        const modal = new bootstrap.Modal(document.getElementById('addAccountModal'));
        modal.hide(); // Close the modal
      },
      error: (err) => {
        console.error('Error adding account:', err);
      },
    });
  }

  deleteAccount(id: string): void {
    this.accountService.deleteAccount(id).subscribe({
      next: () => {
        this.loadAccounts();
      },
      error: (err) => {
        this.errorMessage = 'Failed to delete account. Please try again.';
        console.error(err);
      },
    });
  }
}
