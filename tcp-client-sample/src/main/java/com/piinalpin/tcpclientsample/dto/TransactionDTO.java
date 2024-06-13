package com.piinalpin.tcpclientsample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.UUID;
@Data
@AllArgsConstructor
public class TransactionDTO {
    UUID transactionId;
    double amount;
    Account payer;
    Account payee;
    public TransactionDTO(){
        transactionId=UUID.randomUUID();
    }
}
