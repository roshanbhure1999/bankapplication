package com.bankapplication.dto;

import lombok.Data;

@Data
public class TransferDTO {
    private long accountNumberFrom;
    private long accountNumberTo;
    private long amount;
    private String ifscCode;
    private String name;

}
