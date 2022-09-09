package com.example.javastudy.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock原理：它是可重入的独占锁，也是只能有一个线程可以获取该锁，其他获取该锁的线程会被阻塞被放入该锁的AQS阻塞
 * 队列里面，CAS+AQS队列来实现，它支持公平锁和非公平锁(默认是非公平锁)，他们的不同点是在调用非公平锁的时候当前线程会尝试获取锁，
 * 失败了则调用acquire方法放入AQS中的CLH，而公平锁会直接入队等待
 * 它和synchronized都是可重入锁，可重入锁就是为了防止死锁，假如一个类中的同步方法调用另外一个同步方法，如果不支持同步的话，
 * 进入method2方法的时候当前线程获得锁，method2方法执行method1方法当前线程又要去尝试获取锁，如果不支持重入，他就要等释放，
 * 把自己阻塞，导致自己锁死自己
 *
 */
public class ReentrantLockTest {

    private static volatile int number;

    private static Lock lock = new ReentrantLock();
    private static CountDownLatch count = new CountDownLatch(2);
    static class MyThread implements Runnable{
        @Override
        public void run() {
            try{
                lock.lock();
                for (int i = 0; i < 10000; i++) {
                    number++;
                }

            }finally {
                count.countDown();
                lock.unlock();
            }


        }

    }

    public static void main(String[] args) {

        ReentrantLockTest.MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        t1.start();
        t2.start();
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(number);
    }
//    public static synchronized void add(){
//        number++;
//    }
}
