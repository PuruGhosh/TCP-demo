package com.piinalpin.tcpclientsample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    String accountId;
    String ifsc;
    String holderName;
}
