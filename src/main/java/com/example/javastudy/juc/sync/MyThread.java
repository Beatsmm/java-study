package com.example.javastudy.juc.sync;

public class MyThread implements Runnable{

    private Example example;

    public MyThread(Example example) {
        this.example = example;
    }

    @Override
    public void run() {
        example.printStatic();
        example.print();
    }
}
