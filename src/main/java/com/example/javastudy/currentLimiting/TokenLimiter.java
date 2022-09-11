package com.example.javastudy.currentLimiting;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 令牌桶限流
 * 思想：新的请求到来会从桶中拿走一个令牌，如果桶里没有令牌就拒绝，当然令牌的数量也是有限的，令牌的数量与时间和
 * 发放速度有关，时间越长，桶里面的令牌会越多
 * 规则：
 * 1、按照一个速度像桶中放入令牌
 * 2、令牌的容量有限，但是放行的速度不是固定的，只要桶中还有剩余令牌，请求过来就会放行
 * 3、如果令牌的发放速度慢于请求速度，桶内就没有令牌可领，请求就会被拒绝，可以对突发的流量进行有效应对
 */
public class TokenLimiter {

    // 桶容量
    private static long capacity = 10;
    // 放入令牌的速度
    private static long rate = 2;
    // 上次放置令牌的时间
    private static long lastTime = System.currentTimeMillis();
    // 桶中令牌的剩余量
    private static AtomicLong tokenNum = new AtomicLong();

    public synchronized static boolean tryAcquire(){
        // 更新桶中剩余令牌数量
        long now = System.currentTimeMillis();
        tokenNum.addAndGet((now - lastTime) / 1000 * rate);
        tokenNum.set(Math.min(capacity,tokenNum.get()));
        // 更新时间
        lastTime += (now - lastTime) / 1000 * 1000;
        // 判断是否还有令牌
        if(tokenNum.get()>0){
            tokenNum.incrementAndGet();
            return true;
        }else {
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
