package com.example.javastudy.juc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试当前线程拿到写锁，再去获取读锁
 * 结论：当AQS没有其他线程排队的时候，当前线程能立刻获得读锁，如果目前主线程拿写锁的时候被其他线程先获取到了，那么主线程读锁也拿不到
 */
public class ReentrantReadWriteLockThree {

    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        try{
            writeLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了写锁");
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取到了写锁");

        }finally {
            readLock.unlock();
            writeLock.unlock();
        }
    }

}


