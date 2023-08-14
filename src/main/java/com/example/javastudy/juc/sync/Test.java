package com.example.javastudy.juc.sync;

public class Test {

    public static void main(String[] args) {
//        Example exampleStatic = new Example();
//        Thread t = new Thread(new MyThread(exampleStatic));
//
//
//        // 测试synchronized修饰普通方法上锁住的是谁,由此可见
//        Example example = new Example();
//        Thread t1 = new Thread(new MyThread(example));
//        Thread t2 = new Thread(new MyThread(example));
//        t1.start();
//        t2.start();
//
//        // 现在创建两个对象实例
//        Example example2 = new Example();
//        Thread t3 = new Thread(new MyThread(example2));
//        Example example3 = new Example();
//        Thread t4 = new Thread(new MyThread(example3));
//        t3.start();
//        t4.start();
//        SampleSync sync = new SampleSync();
        for (int i = 0; i < 3; i++) {
            SyncStatic syncStatic = new SyncStatic();
            MyThreadStatic myThreadStatic = new MyThreadStatic(syncStatic);
            myThreadStatic.start();

        }

    }
}
