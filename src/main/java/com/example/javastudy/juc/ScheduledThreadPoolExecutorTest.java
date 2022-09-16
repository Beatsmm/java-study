package com.example.javastudy.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 它可以在多线程环境下延迟执行任务，提供三种定时模式
 * 1、延迟任务执行
 *  -任务按照给定的时间延迟delay后开始执行，对应的方法如下：
 *  schedule(Runnable command,long delay,TimeUnit unit)
 *  schedule(Callable<V> callable,long delay,TineUnit unit)
 * 2、固定延迟的定期执行
 *  -任务第一次按照给定的初始延迟initialDelay执行，后续每一次执行的时间为上一次任务的结束时间加上给定的period
 *  scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
 * 3、按照固定的周期执行
 *  -任务第一次按照给定的初始延迟initialDelay执行，后续每一次执行的时间为固定的时间间隔period，如果线程池中工
 *  作线程不够则任务顺延执行；对应的方法如下：
 *  scheduleWithFixedDelay(Runnable command, long initialDelay, long period, TimeUnit unit)
 *
 *  底层原理
 *  内部实现了一个DelayedWorkQueue，所有的任务都会放到这个队列里，这个阻塞队列内部通过一个数组来保存这些任务
 *  并且基于最小堆排序，按照每个任务的下次执行时间进行排序，这样就保证来执行线程拿到的这个队列中的第一个元素就是
 *  最接近当前时间执行的任务了
 *  从时间上保证是通过DelayedWorkQueue重写了take和poll方法，利用了AQS的ConditionObject机制使当前线程休眠，
 *  等时间到了在唤醒线程去拿第一个任务
 *
 */
public class ScheduledThreadPoolExecutorTest {
    public static void main(String[] args) {
        //1、延迟任务执行
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        System.out.println("start schedule a task");
        executor.schedule(()->{
            System.out.println("task is running");
        },5, TimeUnit.SECONDS);
        // 2、固定延迟的定期执行
        //我们计划了一个定期（每5秒钟）延迟执行的任务，第一次任务立即执行，
        // 每次任务执行时长2秒钟，通过打印的日志我们可以看到每次任务开始执行的时间为：
        // 上次任务结束时间+5秒钟：
        executor.scheduleWithFixedDelay(()->{
            System.out.println("task is running");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,5,TimeUnit.SECONDS);
        //3、按照固定的周期执行,我们创建了一个核心线程池为10的
        //ScheduledThreadPoolExecutor，并计划了一个定期（每5秒钟）执行一次的任务
        // ，过打印的日志我们可以看到每次任务开始执行的时间为：上次任务开始时间+5秒钟：
        ScheduledExecutorService executors = Executors.newScheduledThreadPool(10);
        System.out.print("start schedule a task.");
        executors.scheduleAtFixedRate(() -> {
            System.out.print("task is running.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("task is finished.");

        }, 0, 5, TimeUnit.SECONDS);
    }
}