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

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: testFanoutMessage ";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("fanoutExchange", null, map);
        return "ok";
    }

    /**
     * Shutdown Signal: channel error;
     * protocol method:
     * #method<channel.close>(reply-code=404, reply-text=NOT_FOUND - no exchange 'non-existent-exchange' in vhost '/', class-id=60, method-id=40)
     * 没有找到交换机，因为没有配置，这种情况触发的是ConfirmCallback
     */
    @GetMapping("/testMessageAck")
    public String TestMessageAck(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: non-existent-exchange test message ";
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("non-existent-exchange", "TestDirectRouting", map);
        return "ok";

    }

    /**
     * ReturnCallback:     消息：(Body:'{createTime=2019-09-04 09:48:01, messageId=563077d9-0a77-4c27-8794-ecfb183eac80, messageData=message: lonelyDirectExchange test message }' MessageProperties [headers={}, contentType=application/x-java-serialized-object, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0])
     * ReturnCallback:     回应码：312
     * ReturnCallback:     回应信息：NO_ROUTE
     * ReturnCallback:     交换机：lonelyDirectExchange
     * ReturnCallback:     路由键：TestDirectRouting
     * ConfirmCallback:     相关数据：null
     * ConfirmCallback:     确认情况：true
     * ConfirmCallback:     原因：null
     * 当配置了交换机以后可以看到两个函数都调了;
     * 这种情况下，消息是推送到成功到服务器的M所以ConfirmCallback对消息的确认的情况是true
     * 在ReturnCallback回调函数的打印参数里面可以看到，消息是推送到了交换机成功了，但是在
     * 路由分发给队列的时候找不到队列，所以报错NO_ROUTE
     * 触发了ConfirmCallback和ReturnCallback两个回调函数
     */
    @GetMapping("/testMessageAck2")
    public String TestMessageAck2(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "message: lonelyDirectExchange test message";
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
        return "ok";

    }
}
