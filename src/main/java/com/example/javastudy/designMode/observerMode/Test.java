package com.example.javastudy.designMode.observerMode;


import com.example.javastudy.designMode.observerMode.event.ScoreDataEvent;
import com.example.javastudy.designMode.observerMode.event.listener.MyScoreListener;
import com.example.javastudy.designMode.observerMode.event.listener.MySmsListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 观察者模式就是当一个行为发生时传递信息给另外一个用户接受做出相应的处理，两者之间没有直接的耦合关系，
 * 家兔我们经常使用的MQ服务，虽然MQ服务是有一个通知中心并不是每一个类服务进行通知，但是总体上也可以算
 * 作是观察者模式的思路设计，再比如可能做过一些类似事件监听总线，让主线服务与其他辅线业务分离，为了使
 * 系统降低耦合和增强扩展，也会使用观察者模式处理

 * 背景：这就是一个观察者模式的生活案例。当领导有事的时候发布通知到群里，群里的所有人收到通知后做相应的事情。
 * 以上案例中可以分为下面几个角色: - 监听者（也可以说是观察者）:群里面每一个人都是一个监听者。
 * - 管理者(对应其他教程中的主题-subject）:也就是群，主要有添加（群成员）监听者，移除（群成员）监听者，
 * 还有通知所有监听者的功能。 - 事件（或者说通知）:也就是领导发到群里面的消息是一个事件（或者说通知）。
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.javastudy.designMode.observerMode");
        LoopObserverImpl loopObserver = context.getBean(LoopObserverImpl.class);
        MyScoreListener scoreL = context.getBean(MyScoreListener.class);
        MySmsListener smsL = context.getBean(MySmsListener.class);
        // 向观察者中注册listener
        loopObserver.registerListener(scoreL);
        loopObserver.removeListener(smsL);
        ScoreDataEvent scoreData = new ScoreDataEvent();
        scoreData.setMsg("循环同步观察者");
        //发布积分事件，通知监听者
        loopObserver.notifyListener(scoreData);
//        // 从Spring容器中获取QueueObserverImpl观察者
        QueueObserverImpl queueObserver = context.getBean(QueueObserverImpl.class);
        //向观察者中注册listener
        queueObserver.registerListener(scoreL);
        queueObserver.registerListener(smsL);
        ScoreDataEvent scoreData1 = new ScoreDataEvent();
        scoreData1.setMsg("队列异步观察者");
        //发布积分事件，通知监听者
        queueObserver.notifyListener(scoreData1);

    }
}
