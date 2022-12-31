package com.demos.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    // 队列起名
    @Bean
    public Queue TestDirectQueue(){
        // durable:是否持久化，默认是false，持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前链接有效
        // exclusive:默认是false，只能被当前创建的链接使用，而且当链接关闭以后队列就会被删除
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会被自动删除

        return new Queue("TestDirectQueue",true);
    }

    // Direct交换机 起名：TestDirectExchange
    @Bean
    DirectExchange TestDirectExchange(){
        return new DirectExchange("TestDirectExchange",true,false);
    }

    // 绑定
    // 将队列和交换机绑定，并设置用于匹配键：TestDirectRouting
    @Bean
    Binding bindingDirect(){
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("TestDirectRouting");
    }

    @Bean
    DirectExchange lonelyDirectExchange(){
        return new DirectExchange("lonelyDirectExchange");
    }
}
