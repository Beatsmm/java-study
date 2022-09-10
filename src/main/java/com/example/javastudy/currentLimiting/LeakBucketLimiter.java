package com.example.javastudy.currentLimiting;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 漏桶限流类似于往水桶里面注水，入桶的速度是任意的，但是出水的速度是固定的，当水超过水桶的容积（也就是服务器的负载）就会
 * 流出去（相当于舍弃请求）
 */
public class LeakBucketLimiter {

    // 桶的容积
    private static long capacity = 10;

    // 出水量
    private static long rate = 2;

    // 剩余水的体积
    private static AtomicLong surplus = new AtomicLong();

    // 开始时间
    private static long startTime = System.currentTimeMillis();
    /**
     * true表示可以通过
     * false表示被限制，丢弃
     */
    public synchronized static boolean tryAcquire() {
        // 如果桶的剩余量为0
        if (surplus.get() == 0) {
            startTime = System.currentTimeMillis();
            surplus.set(1);
            return true;
        }
        // 计算从当前时间到开始时间流出的水与现在剩余的水
        surplus.set(surplus.get() - (System.currentTimeMillis() - startTime) / 1000 * rate);
        // 避免出现<0的情况
        surplus.set(Math.max(0, surplus.get()));
        // 设置新的开始时间
        startTime += (System.currentTimeMillis() - startTime) / 1000 * 1000;
        // 如果当前水小于桶的容量，可以放行
        if (surplus.get() < capacity) {
            surplus.incrementAndGet();
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        // 实际生产中不建议使用Executors
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 被限制的次数
        AtomicInteger limit = new AtomicInteger(0);
        // 线程数量
        int threads = 2;
        // 每条线程执行的次数
        int turns = 20;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        long start = System.currentTimeMillis();
        for (int i = 0; i < threads; i++) {
            executorService.submit(()->{
                try{
                    for (int j = 0; j < turns; j++) {
                        boolean flag = tryAcquire();
                        if (!flag){
                            limit.getAndIncrement();
                        }
                        Thread.sleep(200);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        float time = (System.currentTimeMillis() - start) / 1000F;
        //输出统计结果
        System.out.println("限制的次数为：" + limit.get() +
                ",通过的次数为：" + (threads * turns - limit.get()));
        System.out.println("限制的比例为：" + (float) limit.get() / (float) (threads * turns));
        System.out.println("运行的时长为：" + time + "s");
    }
}
