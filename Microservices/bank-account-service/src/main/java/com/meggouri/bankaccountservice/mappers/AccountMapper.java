package com.meggouri.bankaccountservice.mappers;


import com.meggouri.bankaccountservice.DTOs.AccountDTO;
import com.meggouri.bankaccountservice.entities.Account;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDTO mapAccount(Account account);
    List<AccountDTO> mapAccounts(List<Account> accounts);
}
