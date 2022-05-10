package com.bankapplication.service.impl;

import com.bankapplication.dto.CustomerDto;
import com.bankapplication.dto.TransferDTO;
import com.bankapplication.entity.Account;
import com.bankapplication.entity.Customer;
import com.bankapplication.repository.AccountRepository;
import com.bankapplication.repository.CustomerRepository;
import com.bankapplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String addCustomer(CustomerDto customerDto) {
        String aadharNumber = customerDto.getAadharNumber();
        String panNumber = customerDto.getPanNumber();
        Customer byPanNumberOrAadharNumber = customerRepository.findByPanNumberOrAadharNumber(Optional.ofNullable(customerDto.getPanNumber()),(Optional.ofNullable( customerDto.getAadharNumber())));
        if (byPanNumberOrAadharNumber != null) {
            return "PanNumberOrAadharNumber is not unique";
        }
        if (aadharNumber.length() == 12 && panNumber.length() == 10) {
            customerRepository.save(dtoToEntity(customerDto));
            return "Done";
        }
        return "Invalid aadharNumber/PanNumber";
    }

    private Customer dtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCId(customerDto.getCId());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setCity(customerDto.getCity());
        customer.setState(customerDto.getState());
        customer.setCountry(customerDto.getCountry());
        customer.setZipcode(customerDto.getZipcode());
        customer.setAadharNumber(customerDto.getAadharNumber());
        customer.setPanNumber(customerDto.getPanNumber());

        return customer;

    }

    @Override
    public List<Account> getAccounts(long customerId) {

        List<Account> accounts = accountRepository.findByCustomer(customerRepository.findById(customerId).get());
        return accounts;

    }

    @Override
    public String getBalance(long accountNumber) {
        Account byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        return "This Account Number " +byAccountNumber.getAccountNumber()+" have type "+ byAccountNumber.getAccountType().name() +" Account  and available balance is " +  byAccountNumber.getAmount();
    }

    @Override
    public String transferMoney(TransferDTO transferDTO) {

        Account fromAccount = accountRepository.findByAccountNumber(transferDTO.getAccountNumberFrom());

        long currentAmount = fromAccount.getAmount();
        long accountNumberTo = transferDTO.getAccountNumberTo();
        long amountToBeTransfer = transferDTO.getAmount();
        if (currentAmount > amountToBeTransfer) {
            Account byAccountNumber = accountRepository.findByAccountNumber(accountNumberTo);
            long addBalanceInToAccount = byAccountNumber.getAmount() + transferDTO.getAmount();
            byAccountNumber.setAmount(addBalanceInToAccount);
            accountRepository.save(byAccountNumber);
            long subAmountFromAccount = fromAccount.getAmount() - transferDTO.getAmount();
            fromAccount.setAmount(subAmountFromAccount);
            accountRepository.save(fromAccount);

            return "Successful transaction ";
        }
        return "Insufficient Balance";
    }


}
