package com.example.javastudy.juc;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 阻塞队列，它除了提供队列的FIFO功能
 * 1、当获取队列内容时发现队列是空的，则等待其变为不是空的
 * 2、当往队列存储内容时如果队列是满的，则等待其他线程获取队列内容让其变得可用
 * 有两个子类ArrayBlockingQueue和LinkedBlockingQueue
 */
public class BlockingQueueTest {

    /**
     * ArrayBlockingQueue结构
     * ArrayBlockingQueue是一个基于数组的BlockingQueue，并且数组都是有固定长度的，数组本身是读操作比较有优势，
     * 增加、删除操作大部分情况下是性能比较低的，它内部也是通过ReentrantLock来实现的
     */
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        new Thread(()->{
            while(true){
                try {
                    Integer value = arrayBlockingQueue.take();
                    System.out.println(LocalDateTime.now() + "get" + value + "from arrayBlockingQueue");
//                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    arrayBlockingQueue.put(i);
                    System.out.println(LocalDateTime.now() + "put" + i + "to queue");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
