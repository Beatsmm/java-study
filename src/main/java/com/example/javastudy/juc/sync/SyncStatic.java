package com.example.javastudy.juc.sync;

public class SyncStatic {

    public static synchronized void print(){
        System.out.println(Thread.currentThread().getName()+":开始执行，非静态");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+":结束执行，非静态");

    }

    public void printNoStatic(){
        System.out.println(Thread.currentThread().getName()+":开始执行");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+":结束执行");
    }
}
