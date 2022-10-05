package com.example.javastudy.netty.nettynio;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TestNettyFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("执行计算");
                Thread.sleep(1000);
                return 10;
            }
        });
        // 主线程等待结果 同步
        System.out.println("等待结果");
        future.get();
        // 异步
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                System.out.println("异步接受结果"+future.getNow());
            }
        });
    }
}
