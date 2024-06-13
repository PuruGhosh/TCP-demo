package com.piinalpin.tcpclientsample.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 497439089321548030L;

    private String message;
    private String sender;
    private String comment;
    private String reciever;
    private String timestamp;

}
