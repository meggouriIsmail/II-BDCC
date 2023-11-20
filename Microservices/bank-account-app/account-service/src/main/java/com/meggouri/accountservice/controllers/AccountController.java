package com.meggouri.accountservice.controllers;

import com.meggouri.accountservice.clients.CustomerRestClient;
import com.meggouri.accountservice.entities.BankAccount;
import com.meggouri.accountservice.modeles.Customer;
import com.meggouri.accountservice.repositories.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    private BankAccountRepository accountRepository;
    private CustomerRestClient customerRestClient;

    public AccountController(BankAccountRepository accountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = accountRepository;
        this.customerRestClient = customerRestClient;
    }
    @GetMapping("/accounts")
    public List<BankAccount> accountList(){
        List<BankAccount> accountList = accountRepository.findAll();
        accountList.forEach(acc->{
            acc.setCustomer(customerRestClient.findCustomerById(acc.getCustomerId()));
        });
        return accountList;
    }
    @GetMapping("/accounts/{id}")
    public BankAccount bankAccountById(@PathVariable String id){
        BankAccount bankAccount= accountRepository.findById(id).get();
        bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
}
