package com.meggouri.bankaccountservice.controllers;

import com.meggouri.bankaccountservice.entities.Account;
import com.meggouri.bankaccountservice.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class graphController {
    private AccountRepository accountRepository;

    @QueryMapping
    public List<Account> accountsList(){
        return accountRepository.findAll();
    }
    @QueryMapping
    public Account accountById(@Argument String id){
        return accountRepository.findById(id).get();
    }
}
