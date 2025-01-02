package com.meggouri.accountservice.controllers;

import com.meggouri.accountservice.clients.CustomerRestClient;
import com.meggouri.accountservice.entities.BankAccount;
import com.meggouri.accountservice.modeles.Customer;
import com.meggouri.accountservice.repositories.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class AccountController {
    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

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

    @PostMapping("/accounts")
    public BankAccount addAccount(@RequestBody BankAccount bankAccount) {
        bankAccount.setAccountId(UUID.randomUUID().toString());
        bankAccount.setCreateAt(LocalDate.now());
        return accountRepository.save(bankAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        if (!accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
