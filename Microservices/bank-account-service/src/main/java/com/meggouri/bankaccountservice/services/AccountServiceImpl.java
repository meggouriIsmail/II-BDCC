package com.meggouri.bankaccountservice.services;

import com.meggouri.bankaccountservice.DTOs.AccountDTO;
import com.meggouri.bankaccountservice.entities.Account;
import com.meggouri.bankaccountservice.mappers.AccountMapper;
import com.meggouri.bankaccountservice.repositories.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private AccountMapper accountMapper;

    @Override
    public List<AccountDTO> getAllAccounts() {
        return accountMapper.mapAccounts(accountRepository.findAll());
    }

    @Override
    public AccountDTO getAccountById(String id) {
        return accountMapper.mapAccount(accountRepository.findById(id).get());
    }

    @Override
    public AccountDTO saveAccount(Account account) {
        return accountMapper.mapAccount(accountRepository.save(account));
    }

    @Override
    public AccountDTO putAccount(Account account, String id) {
        Account toModify = accountRepository.findById(id).get();
        toModify.setBalance(account.getBalance());
        toModify.setCurrency(account.getCurrency());
        toModify.setCreatedAt(account.getCreatedAt());
        return accountMapper.mapAccount(accountRepository.save(toModify));
    }
}
