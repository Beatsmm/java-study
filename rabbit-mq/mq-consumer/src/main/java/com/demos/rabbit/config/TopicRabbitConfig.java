package com.demos.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {


    // 绑定键
    public final static String man = "topic.man";
    public final static String woman = "topic.woman";

    @Bean
    public Queue firstQueue(){
        return new Queue(man);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(woman);
    }

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("topicExchange");
    }

    // 将firstQueue和topicExchange绑定，而且绑定的键值为topic.man(这样只要消息携带的路由是topic.man才会分发到队列)
    @Bean
    Binding bindingExchangeMessage(){
        return BindingBuilder.bind(firstQueue()).to(exchange()).with(man);
    }

    // 同理将secondQueue和topicExchange绑定，而且绑定的键值为用上通配符路由规则topic.#（#表示0个或者多个）
    // 这样只要消息携带的路由建是由topic.开头的都会分发到这个队列
    @Bean
    Binding bindingExchangeMessage2(){
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }
}
