package com.demos.rabbit.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "first message,hello!";
        Map<String,Object> map = new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        rabbitTemplate.convertAndSend("TestDirectExchange","TestDirectRouting",map);
        return "ok";
    }

    @GetMapping("/sendTopicMessageFirst")
    public String sendTopicMessageFirst(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message:man";
        Map<String,Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", LocalDateTime.now());
        rabbitTemplate.convertAndSend("topicExchange","topic.man",manMap);
        return "ok";
    }

    @GetMapping("/sendTopicMessageSecond")
    public String sendTopicMessageSecond(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message->women is liuyifei";
        Map<String,Object> manMap = new HashMap<>();
        manMap.put("messageId", messageId);
        manMap.put("messageData", messageData);
        manMap.put("createTime", createTime);
        rabbitTemplate.convertAndSend("topicExchange", "topic.woman",manMap);
        return "ok";
    }
}
