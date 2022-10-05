package com.example.javastudy.netty.nettynio;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

public class TestNettyPromise {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1、准备EventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();

        //2、可以主动创建promise，结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);
        new Thread(() -> {
            //3、任意一个线程执行计算，计算完毕后向promise填充结果
            System.out.println("开始计算");
            try{
                Thread.sleep(1000);
                promise.setSuccess(20);
            }catch (Exception e){
                e.printStackTrace();
                promise.setFailure(e);
            }
        }).start();
        // 4、接受结果的线程
        System.out.println(promise.get());
    }
}
