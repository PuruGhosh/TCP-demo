package com.piinalpin.tcpclientsample.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.piinalpin.tcpclientsample.dto.Content;
import com.piinalpin.tcpclientsample.dto.JsonDto;
import com.piinalpin.tcpclientsample.dto.MessageDTO;
import com.piinalpin.tcpclientsample.dto.TransactionDTO;
import com.piinalpin.tcpclientsample.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("tcpSend")
public class TcpClientController {

  private static final Logger log = LoggerFactory.getLogger(TcpClientController.class);
  private final MessageService messageService;
  @Value("${spring.application.name}")
  private String SENDER;

  public TcpClientController(MessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping(value = "/send")
  public MessageDTO send(@RequestParam("message") String message) {
    return messageService.send(message);
  }

  @GetMapping(value = "/sendXml", consumes = MediaType.APPLICATION_XML_VALUE)
  public TransactionDTO sendXml(@RequestBody TransactionDTO payload)
      throws JsonProcessingException {
    log.info(String.valueOf(payload));
    var xmlMapper = new XmlMapper();
    messageService.send(xmlMapper.writeValueAsString(payload));
//    messageService.send(payload.toString());
    return payload;
  }

  @GetMapping(value = "/sendJson", consumes = MediaType.APPLICATION_JSON_VALUE)
  public MessageDTO sendJson(@RequestBody Content payload)
          throws JsonProcessingException {
    log.info("Received payload {}", payload);
    var mapper = new ObjectMapper();
    var jsonObject = new JsonDto();
    jsonObject.setSender(SENDER);
    if(payload.getId()==null){
      payload.setId(UUID.randomUUID());
    }
    jsonObject.setContent(payload);
    var msg = messageService.send(mapper.writeValueAsString(jsonObject));
    return msg;
  }
}
