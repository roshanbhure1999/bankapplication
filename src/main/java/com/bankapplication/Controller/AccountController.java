package com.bankapplication.Controller;

import com.bankapplication.dto.AccountDto;
import com.bankapplication.entity.Account;
import com.bankapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public String createAccount(@RequestBody AccountDto accountDto) {
        return accountService.addAccount(accountDto);
    }

    @GetMapping(path = "/{accId}")
    public Optional<Account> getAmount(@PathVariable("accountId") long accId) {
        return accountService.getAccount(accId);
    }
}
