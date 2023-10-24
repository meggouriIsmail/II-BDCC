package com.meggouri.bankaccountservice.services;

import com.meggouri.bankaccountservice.DTOs.AccountDTO;
import com.meggouri.bankaccountservice.entities.Account;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> getAllAccounts();
    AccountDTO getAccountById(String id);
    AccountDTO saveAccount(Account account);
    AccountDTO putAccount(Account account, String id);
}
