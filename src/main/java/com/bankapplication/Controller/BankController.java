package com.bankapplication.Controller;

import com.bankapplication.dto.BankDto;
import com.bankapplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping
    public String saveBank(@RequestBody BankDto bankDto) {
     return  bankService.addBank(bankDto);
    }


}
