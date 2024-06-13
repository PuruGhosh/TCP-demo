package com.piinalpin.tcpserversample.service;

import com.piinalpin.tcpserversample.dto.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Service
public class MessageService {

    public MessageDTO process(byte[] message) throws IOException {
        String messageJson = new String(message);
        Jackson2JsonObjectMapper mapper = new Jackson2JsonObjectMapper();
        MessageDTO messageDTO = mapper.fromJson(messageJson, MessageDTO.class);
        log.info("Message: {}, from: {}, at: {}", messageDTO.getMessage(), messageDTO.getSender(), messageDTO.getTimestamp());

        MessageDTO response = MessageDTO.builder()
                .message(messageDTO.getMessage())
                .comment("Hello this message from TCP server!")
                .timestamp(LocalDateTime.now().toString())
                .sender("tcp-server")
                .reciever(messageDTO.getSender())
                .build();
        return response;
    }

}
