package com.meggouri.bankaccountservice.controllers;

import com.meggouri.bankaccountservice.DTOs.AccountDTO;
import com.meggouri.bankaccountservice.DTOs.AccountRequest;
import com.meggouri.bankaccountservice.entities.Account;
import com.meggouri.bankaccountservice.repositories.AccountRepository;
import com.meggouri.bankaccountservice.services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class AccountController {
    private AccountServiceImpl accountService;

    @GetMapping("/accounts")
    private List<AccountDTO> getAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/saveAccount")
    private AccountDTO saveAccount(@RequestBody AccountRequest account){
        return accountService.saveAccount(account);
    }

    @PutMapping("/{id}")
    private AccountDTO modifyAccount(@RequestBody AccountRequest account, @PathVariable("id") String id){
        return accountService.updateAccount(account, id);
    }
}
