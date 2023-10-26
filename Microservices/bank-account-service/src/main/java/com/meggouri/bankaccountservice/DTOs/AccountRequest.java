package com.meggouri.bankaccountservice.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private Date createdAt;
    private Double balance;
    private String currency;
}
