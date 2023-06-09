package com.demos.rabbit.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对应的手动确认消息监听类，MyAckReceiver.java（手动确认模式需要实现 ChannelAwareMessageListener）：
 * 之前的相关监听器可以先注释掉，以免造成多个同类型监听器都监听同一个队列。
 */
@Component
public class MyAckReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //因为传递消息的时候用的map传递,所以将Map从Message内取出需要做些处理
            String msg = message.toString();
            String[] msgArray = msg.split("'");//可以点进Message里面看源码,单引号直接的数据就是我们的map消息数据
            Map<String, String> msgMap = mapStringToMap(msgArray[1].trim(),3);
            String messageId=msgMap.get("messageId");
            String messageData=msgMap.get("messageData");
            String createTime=msgMap.get("createTime");
            System.out.println("  MyAckReceiver  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            /**
             *  如果需要监听多个队列并且每个队列都有自己的处理逻辑
             * if ("TestDirectQueue".equals(message.getMessageProperties().getConsumerQueue())){
             *                 System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
             *                 System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
             *                 System.out.println("执行TestDirectQueue中的消息的业务处理流程......");
             *             }
             *
             * if ("fanout.A".equals(message.getMessageProperties().getConsumerQueue())){
             *                 System.out.println("消费的消息来自的队列名为："+message.getMessageProperties().getConsumerQueue());
             *                 System.out.println("消息成功消费到  messageId:"+messageId+"  messageData:"+messageData+"  createTime:"+createTime);
             *                 System.out.println("执行fanout.A中的消息的业务处理流程......");
             *
             *  }

             */
            channel.basicAck(deliveryTag,true);// 第二个参数，手动确认可以被批处理，这个参数为true的时候，可以一次性确认delivery_tag 小于等于传入值的所有消息
           // channel.basicReject(deliveryTag, true);//第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
        } catch (IOException e) {
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();
        }
    }

    //{key=value,key=value,key=value} 格式转换成map
    private Map<String, String> mapStringToMap(String str,int entryNum ) {
        str = str.substring(1, str.length() - 1);
        String[] strs = str.split(",",entryNum);
        Map<String, String> map = new HashMap<String, String>();
        for (String string : strs) {
            String key = string.split("=")[0].trim();
            String value = string.split("=")[1];
            map.put(key, value);
        }
        return map;
    }

}
