package com.example.javastudy.juc.sync;

public class MyThreadStatic extends Thread{

    private SyncStatic syncStatic;

    public MyThreadStatic(SyncStatic syncStatic){
        this.syncStatic = syncStatic;
    }

    @Override
    public void run() {
        syncStatic.print();
    }
}
