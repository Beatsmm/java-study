package com.example.javastudy.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock底层实现了reentrantLock接口，具有读锁（共享锁）和写锁（排他锁）
 * 读锁：共享锁，也分公平锁和非公平锁，在没有写锁在执行的时候或者在AQS排队的时候，两者没有区别
 * 例子
 * 1、当前只有读锁的时候：所有线程都能获取锁
 * 2、有线程拿了写锁，并且不是当前线程，当前线程进入等待队列，等写锁释放了才有可能获取读锁
 * 3、当前线程拿到了写锁，再去获取读锁
 */
public class ReentrantReadWriteLockOne {

    //例子2：如果一个线程先获取到了写锁，那么另外一个线程不管获取读锁还是写锁进入等待队列，直到另外一个线程释放
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Thread readThread = new Thread(new MyThread(readLock));
        Thread writeThread = new Thread(new MyThread(writeLock));
        writeThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        readThread.start();
    }
}
class MyThread implements Runnable{
    private Lock lock;

    public MyThread(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"now is working");
        try {
            // 确保别的线程能拿到读锁
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "finally working");
            lock.unlock();
        }
    }
}