package com.example.javastudy.juc;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    /**
     * AtomicInteger源码运用了Unsafe类实现cas安全的自增，还定义了全局valueOffset->保存在内存里面的偏移量，真正的值
     * value是一个被volatile修饰的，它里面的CAS操作就是compareAndSwapInt，每次从内存中根据内存偏移量valueOffset
     * 取出数据跟期望的值比较，一致就把内存重的值修改保证volatile没有保证的原子性
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        AtomicTest.MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        t1.start();
        t2.start();
        Thread.sleep(500);
        //AtomicInteger=20100 int<20100
        System.out.println("AtomicInteger="+myThread.atomicNumber.get()+"  int="+myThread.intNumber);
    }

    static class MyThread implements Runnable{
        AtomicInteger atomicNumber = new AtomicInteger(100);
        int intNumber = 100;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                atomicNumber.incrementAndGet();
                intNumber++;
            }
        }
    }
}
