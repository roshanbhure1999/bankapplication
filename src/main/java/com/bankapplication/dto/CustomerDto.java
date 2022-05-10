package com.bankapplication.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private long cId;
    private String name;
    private String mobileNumber;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String aadharNumber;
    private String panNumber;

}
