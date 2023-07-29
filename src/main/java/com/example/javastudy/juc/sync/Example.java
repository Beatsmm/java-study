package com.example.javastudy.juc.sync;

public class Example {

    public synchronized void print(){
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static synchronized void printStatic(){
        while (true){
            System.out.println(Thread.currentThread().getName());
        }
    }
}
