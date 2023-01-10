package com.demos.rabbit;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

/**消息接受
 * 1、消费者和Broker建立TCP连接
 * 2、消费者和Broker建立通道（channel）
 * 3、消费者监听指定的Queue（队列）
 * 4、当由消息到达Queue的时候将消息推送给消费者
 * 5、消费者接受到消息返回ACK回复
 */
public class RabbitConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitConsumerApplication.class, args);
    }
}
