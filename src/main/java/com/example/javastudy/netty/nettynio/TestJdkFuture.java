package com.example.javastudy.netty.nettynio;

import java.util.concurrent.*;

/**
 * Future其实就是一个空的书包，一个线程把这个空书包交给另外一个线程，等到这个书包不为空了就拿回来
 * 这就是线程之间通信的一个容器
 */
public class TestJdkFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1、线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 2、提交任务
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);

                return 50;
            }
        });

        // 3、主线程通过future来获取结果，future.get是阻塞方法
        future.get();
    }

}
