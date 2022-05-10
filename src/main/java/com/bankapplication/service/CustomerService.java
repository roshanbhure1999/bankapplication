package com.bankapplication.service;

import com.bankapplication.dto.CustomerDto;
import com.bankapplication.dto.TransferDTO;
import com.bankapplication.entity.Account;

import java.util.List;

public interface CustomerService {
    String addCustomer(CustomerDto customerDto);

    List<Account> getAccounts(long customerId);

    String getBalance(long accountNumber);

    String transferMoney(TransferDTO transferDTO);
}
