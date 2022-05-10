package com.bankapplication.service;

import com.bankapplication.dto.AccountDto;
import com.bankapplication.entity.Account;

import java.util.Optional;

public interface AccountService {
    String addAccount(AccountDto accountDto);

    Optional<Account> getAccount(long id);
}
