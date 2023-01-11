package com.demos.rabbit.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机
 */
@Configuration
public class FanoutExchangeConfig {

    /**
     * 创建三个对了：fanout.A fanout.B fanout.C
     * 将三个队列都绑定在交换机fanoutExchange上
     * 扇形交换机不需要配置路由键
     */
    @Bean
    public Queue queueA(){
        return new Queue("fanout.A",true);
    }

    @Bean
    public Queue queueB(){
        return new Queue("fanout.B",true);
    }

    @Bean
    public Queue queueC(){
        return new Queue("fanout.C",true);
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(){
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB(){
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC(){
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }

}
