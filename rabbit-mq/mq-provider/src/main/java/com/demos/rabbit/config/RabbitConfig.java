package com.demos.rabbit.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {

    /**
     * 生产者推送消息的消息确认调用回调函数已经完毕
     * 可以看到下面写了两个回调函数，ConfirmCallback和ReturnCallback
     * 触发条件：
     * 先从总体的情况分析，推送消息存在四种情况：
     * 1、消息推送到server，但是在server里面没找到交换机
     * 2、消息推送到server，找到交换机了，但是没找到队列
     * 3、消息推送到server，交换机和队列啥都没找到
     * 4、消息推送成功
     */

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 设置开启Mandatory,才能触发回调函数,无论消息推送结果怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData,ack,cause) -> {
            System.out.println("ConfirmCallback:     "+"相关数据："+correlationData);
            System.out.println("ConfirmCallback:     "+"确认情况："+ack);
            System.out.println("ConfirmCallback:     "+"原因："+cause);

        });

        rabbitTemplate.setReturnsCallback(new RabbitTemplate.ReturnsCallback() {
            @Override
            public void returnedMessage(ReturnedMessage returnedMessage) {
                log.info("ReturnCallback:     " + "消息：" + returnedMessage.getMessage());
                log.info("ReturnCallback:     " + "回应码：" + returnedMessage.getReplyCode());
                log.info("ReturnCallback:     " + "回应信息：" + returnedMessage.getReplyText());
                log.info("ReturnCallback:     " + "交换机：" + returnedMessage.getExchange());
                log.info("ReturnCallback:     " + "路由键：" + returnedMessage.getRoutingKey());
            }
        });
        return rabbitTemplate;
    }
}
