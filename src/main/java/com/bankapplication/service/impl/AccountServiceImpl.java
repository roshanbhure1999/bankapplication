package com.bankapplication.service.impl;

import com.bankapplication.dto.AccountDto;
import com.bankapplication.entity.Account;
import com.bankapplication.entity.Bank;
import com.bankapplication.entity.Customer;
import com.bankapplication.entity.SavingOrCurrent;
import com.bankapplication.repository.AccountRepository;
import com.bankapplication.repository.BankRepository;
import com.bankapplication.repository.CustomerRepository;
import com.bankapplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;



    @Override
    public String addAccount(AccountDto accountDto) {
        Account account = dtoToEntity(accountDto);
        if (accountRepository.findByAccountNumber(account.getAccountNumber()) != null) {
            return "Account number is not unique";
        }
        if (account.getBank() == null) {
            return "Bank is not present for the given bankId ";
        }
        if (account.getCustomer() == null) {
            return "Customer is not present for the given customerId ";
        }
        List<Account> byCustomerAndBankAndAccountType = accountRepository.findByCustomerAndBankAndAccountType(account.getCustomer(), account.getBank(), account.getAccountType());

        if (byCustomerAndBankAndAccountType.size() > 0) {
            return "customer already have " + account.getAccountType().name() + " account ";
        } else if ((account.getAccountType().name() == SavingOrCurrent.SAVING.name()) && (account.getAmount() < 5000)) {
            return "Minimum Balance for saving account 5000";
        } else if ((account.getAccountType().name() == SavingOrCurrent.CURRENT.name()) && (account.getAmount() < 10000)) {
            return "Minimum Balance for Current account 10000";
        }
        accountRepository.save(account);
        return "Done New Account";
    }

    private Account dtoToEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setAccountNumber(accountDto.getAccountNumber());
        account.setAccountType(accountDto.getAccountType());
        account.setAmount(accountDto.getAmount());
        Bank bank = bankRepository.findById(accountDto.getBankId()).orElse(null);
        account.setBank(bank);
        Customer customer = customerRepository.findById(accountDto.getCustomerId()).orElse(null);
        account.setCustomer(customer);
        return account;
    }

    @Override
    public Optional<Account> getAccount(long id) {
        return accountRepository.findById(id);
    }
}
