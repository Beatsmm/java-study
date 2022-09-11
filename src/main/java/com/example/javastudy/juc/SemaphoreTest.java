package com.example.javastudy.juc;

import java.time.LocalDateTime;
import java.util.concurrent.Semaphore;

/**
 * 信号量(Semaphore)是一种乐观锁(类似于读写锁中的读锁)，用途是允许一定数量的线程获取锁也就是限制一定数量的线程访问某个资源
 */
public class SemaphoreTest {
    private static Semaphore semaphore = new Semaphore(3);
    public static void main(String[] args) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    System.out.println(LocalDateTime.now()+Thread.currentThread().getName());
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        };

        for (int i = 0; i < 12; i++) {
            new Thread(runnable).start();
        }
    }

    /**
     * 2022-09-11T23:09:52.978Thread-1
     * 2022-09-11T23:09:52.978Thread-0
     * 2022-09-11T23:09:52.978Thread-2
     * 2022-09-11T23:09:54.981Thread-4
     * 2022-09-11T23:09:54.982Thread-5
     * 2022-09-11T23:09:54.981Thread-3
     * 2022-09-11T23:09:56.986Thread-7
     * 2022-09-11T23:09:56.987Thread-8
     * 2022-09-11T23:09:56.986Thread-6
     * 2022-09-11T23:09:58.989Thread-10
     * 2022-09-11T23:09:58.989Thread-9
     * 2022-09-11T23:09:58.991Thread-11
     * 通过结果得到，因为我们限制的信号量为3，同时启动了12个线程去执行，从打印的结果看，同时只有3个线程能获得信号量
     * 它也支持公平和非公平
     */

}
