package com.meggouri.bankaccountservice.controllers;

import com.meggouri.bankaccountservice.DTOs.AccountDTO;
import com.meggouri.bankaccountservice.DTOs.AccountRequest;
import com.meggouri.bankaccountservice.entities.Account;
import com.meggouri.bankaccountservice.mappers.AccountMapper;
import com.meggouri.bankaccountservice.repositories.AccountRepository;
import com.meggouri.bankaccountservice.services.AccountServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class graphController {
    private AccountRepository accountRepository;
    private AccountServiceImpl accountService;

    @QueryMapping
    public List<Account> accountsList(){
        return accountRepository.findAll();
    }
    @QueryMapping
    public Account accountById(@Argument String id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("This account %s doesn't exist",id)));
    }
    @MutationMapping
    public AccountDTO saveAccount(@Argument AccountRequest accountRequest) {
        return accountService.saveAccount(accountRequest);
    }
    @MutationMapping
    public AccountDTO updateAccount(@Argument String id, @Argument AccountRequest accountRequest) {
        return accountService.updateAccount(accountRequest, id);
    }
    @MutationMapping
    public AccountDTO deleteAccount (@Argument String id) {
        Account a =  accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("This account %s doesn't exist",id)));
        accountRepository.delete(a);
        return AccountMapper.INSTANCE.mapAccount(a);
    }
}
