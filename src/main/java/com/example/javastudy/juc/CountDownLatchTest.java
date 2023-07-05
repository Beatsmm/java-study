package com.example.javastudy.juc;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch：同步器，允许一个或者多个线程去等待其他线程完成操作
 * 举例：王者荣耀加载的时候都需要双方加载到百分之百才能开始，通过CountDownLatch来计数，都提交后主线程宣布开始
 */
public class CountDownLatchTest {
    /**
     * 1、接受一个int参数，表示要等待的工作线程的个数
     * 2、await当前线程去同步队列等待，直到latch的值被减到0或者当前线程被终端，队列里面的线程被唤醒
     *    还有一个重载的方法带超时时间
     * 3、countDown() 使latch的值减1，如果减到了0，则会唤醒所有等待在这个latch上的线程
     * 4、getCount，获得latch的值
     */
    public static void main(String[] args) {
        // 用两个线程模拟两个用户
        CountDownLatch countDownLatch = new CountDownLatch(3);
        long start = System.currentTimeMillis();
        System.out.println("开始时间"+System.currentTimeMillis());
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"加载到了100%");
            countDownLatch.countDown();
        },"ThreadA").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"加载到了100%");
            countDownLatch.countDown();
        },"ThreadB").start();
        new Thread(() -> {
            try{
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"加载到了100%");
            countDownLatch.countDown();
        },"ThreadC").start();
        // 让主线程等待两个用户线程都加载完毕
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("准备完毕开始游戏"+time);
    }
}
