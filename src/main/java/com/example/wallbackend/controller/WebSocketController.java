package com.example.wallbackend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller(value = "controller")
public class WebSocketController {


    private final SimpMessagingTemplate template;


    @Autowired
    WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }


    @MessageMapping(value = "/send/message")
    public void onRecivedMessage(String message){
        this.template.convertAndSend("/message",new SimpleDateFormat("HH:mm:ss").format(new Date())+ "-" + message);
    }


}
