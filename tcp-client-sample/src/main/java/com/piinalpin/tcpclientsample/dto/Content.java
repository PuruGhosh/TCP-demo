package com.piinalpin.tcpclientsample.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Content {
    private UUID id;
    private String payer;
    private String payee;
    private Double amount;
}
