package com.example.javastudy.juc.sync;

public class SampleSync {

    public synchronized void sync(){
        System.out.println("线程开始执行");

        // 模拟处理业务
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("处理业务完毕");
    }
}
