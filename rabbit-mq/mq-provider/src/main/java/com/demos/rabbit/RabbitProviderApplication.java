package com.demos.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 生产者->交换机->队列->客户端->
 * borker:消息队列服务进程，包括Exchange和Queue
 * Exchange：消息队列交换机，按一定规则将消息路由转发到某个队列，进行消息过滤
 * Queue:消息队列，存储消息的队列，消息到达队列转发给指定的消费者
 * Producer：消息生产者
 * Consumer：消息消费者，即消费方客户端，接受MQ转发的消息
 *
 * 消息生产：
 * 1、生产者和Broker建立TCP连接
 * 2、生产者和Broker建立通道
 * 3、生产者通过通道消息发送给Broker，由Exchange将消息转发到指定的Queue
 *
 */
@SpringBootApplication
public class RabbitProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProviderApplication.class, args);
    }
}
