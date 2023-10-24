package com.meggouri.bankaccountservice.repositories;

import com.meggouri.bankaccountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
