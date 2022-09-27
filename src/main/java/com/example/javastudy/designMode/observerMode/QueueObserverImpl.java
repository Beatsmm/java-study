package com.example.javastudy.designMode.observerMode;

import com.example.javastudy.designMode.observerMode.event.DataEvent;
import com.example.javastudy.designMode.observerMode.event.MyListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Component
public class QueueObserverImpl implements ObserverInterface<MyListener> {
    //监听者的注册列表
    private List<MyListener> listenerList = new ArrayList<>();
    //创建一个大小为10的阻塞队列
    private BlockingQueue<DataEvent> queue = new LinkedBlockingQueue<>(10);
    //创建一个线程池
    private ExecutorService executorService = new ScheduledThreadPoolExecutor(1, r -> {
        Thread t = new Thread(r);
        t.setName("com.kangarooking.observer.worker");
        t.setDaemon(false);
        return t;
    });

    @Override
    public void registerListener(MyListener myListener) {
        listenerList.add(myListener);
    }

    @Override
    public void removeListener(MyListener myListener) {
        listenerList.remove(myListener);
    }

    @Override
    public void notifyListener(DataEvent event) {
        System.out.println("向队列放入DataMsg：" + event.getMsg());
        queue.offer(event);
    }

    @PostConstruct
    public void initObserver(){
        System.out.println("初始化时启动一个线程");
        executorService.submit(() -> {
            while (true) {
                try {
                    System.out.println("循环从阻塞队列里面获取数据，take是阻塞队列没有数据就会阻塞住");
                    DataEvent dataMsg = queue.take();
                    System.out.println("从阻塞队列获取到数据：" + dataMsg.getMsg());
                    eventNotify(dataMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void eventNotify(DataEvent event) {
        System.out.println("循环所有的监听者");
        for (MyListener myListener : listenerList) {
            myListener.onEvent(event);
        }
    }
}
