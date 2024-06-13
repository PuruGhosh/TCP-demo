package com.piinalpin.tcpserversample.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piinalpin.tcpserversample.config.TCPServerConfiguration;
import com.piinalpin.tcpserversample.dto.MessageDTO;
import com.piinalpin.tcpserversample.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;

import java.io.IOException;

@Slf4j
@MessageEndpoint
public class TCPMessageEndpoint {

    private final MessageService messageService;

    public TCPMessageEndpoint(MessageService messageService) {
        this.messageService = messageService;
    }

    @ServiceActivator(inputChannel = TCPServerConfiguration.TCP_DEFAULT_CHANNEL)
    public byte[] process(byte[] message) throws IOException {
        var response = messageService.process(message);
        log.info("Response message: {}, sender: {}",response.getMessage(),response.getSender());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(response).getBytes();
    }

}
