package com.bankapplication.Controller;

import com.bankapplication.dto.CustomerDto;
import com.bankapplication.dto.TransferDTO;
import com.bankapplication.entity.Account;
import com.bankapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @GetMapping(path = "/{custId}")
    public List<Account> getAccounts(@PathVariable("custId") long id) {
        return customerService.getAccounts(id);
    }

    @GetMapping(path = "/get-balance/{account-number}")
    public String getBalance(@PathVariable("account-number") long accountNumber) {
        return customerService.getBalance(accountNumber);
    }

    @PutMapping(path = "/transfer")
    public String  transferMoney(@RequestBody TransferDTO transferDTO){

        return customerService.transferMoney(transferDTO);
    }

}
