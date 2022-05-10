package com.bankapplication.dto;

import lombok.Data;

@Data
public class BankDto {
    private long bId;
    private String bankName;
    private String ifscCode;
    private String branchName;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    
}
