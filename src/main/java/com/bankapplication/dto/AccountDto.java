package com.bankapplication.dto;

import com.bankapplication.entity.SavingOrCurrent;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountDto implements Serializable {
    private long accId;
    private long accountNumber;
    private SavingOrCurrent accountType;
    private long amount;
    private long customerId;
    private long bankId;
}
