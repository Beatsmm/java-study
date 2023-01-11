package com.demos.rabbit.config;


import com.demos.rabbit.consumer.MyAckReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * 消费者消息确认机制
 * 和生产者确认机制不同，因为消息接收本来就是在监听消息，符合条件的消息就会消费下来
 * 二种模式
 * 1、自动确认，这也是默认的消息确认情况，RabbitMq成功将消息发出也就是消息成功写入TCP Socket中
 * 立即认为本次投递已经被正确处理，不管消费者端是否成功处理本次投递，所以这种情况如果消费端消费
 * 逻辑抛出异常，也就是消费者没有处理成功这条消息，那么就相当于丢失了消息，一般这种情况就相当于消息丢失
 * 一般这种情况都是使用try catch捕捉异常后，打印日志用于追踪数据，这样可以找出对应数据在做后续处理
 * 2、手动确认，我们配置接受消息确认机制时，多数选择的模式
 * 消费者收到消息后，手动调用basic.ack/basic.nack/basic.reject后，RabbitMQ收到这些消息后，才认为本次投递成功。
 * basic.ack-用于肯定确认
 * basic.nack-用于否定确认
 * basic.reject-用于否定确认，但与basic.nack相比有一个限制，一次只能拒绝单条消息
 * 后两个表示没有被正确处理
 * reject:有时候一些场景是需要重新入列的
 * channel.basicReject(deliveryTag,true)拒绝消费当前消息，如果第二个参数传入true,就是将数据重新丢回队列，那么下次
 * 还会消费这条消息，但是设置为false就是告诉服务器我已经知道了这条消息数据了，因为一些原因拒绝它，而且服务器把这个消息丢掉就行
 * 下次不会在消费这条消息了，但是拒绝后重新入列这个确认模式要谨慎，因为一般都是出异常的时候，catch异常在拒绝入列，选择是否重新入列
 * 但是如果使用不当会导致一些每次都被你重入列的消息一直消息-入列-消费-入列这样循环会导致消息挤压
 * nack:channel.basicNack(deliveryTag,false,true)
 * 第一个参数依然是当前消息到的数据的唯一id
 * 第二个参数是指是否针对多条消息，如果是true也就是说一次性针对当前通道的消息的tagID小于当前这条消息的都拒绝确认
 * 第三个参数是指是否重新入列，也就是指不缺认都消息是否重新丢回到队列里面去
 * 同样使用不确认后重新入列这个确认模式要谨慎，因为这里也可能因为考虑不周出现消息一只被重新丢回去的情况导致挤压
 */
@Configuration
public class MessageListenerConfig {

    @Resource
    private CachingConnectionFactory connectionFactory;

    @Resource
    private MyAckReceiver myAckReceiver;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // RabbitMQ默认是自动确认，这里改为手动确认消息
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //设置一个队列
        container.setQueueNames("TestDirectQueue");
        //如果同时设置多个如下： 前提是队列都是必须已经创建存在的
        //  container.setQueueNames("TestDirectQueue","TestDirectQueue2","TestDirectQueue3");

        //另一种设置队列的方法,如果使用这种情况,那么要设置多个,就使用addQueues
        //container.setQueues(new Queue("TestDirectQueue",true));
        //container.addQueues(new Queue("TestDirectQueue2",true));
        //container.addQueues(new Queue("TestDirectQueue3",true));
        container.setMessageListener(myAckReceiver);

        return container;
    }







}
