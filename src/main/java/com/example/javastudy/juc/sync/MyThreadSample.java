package com.example.javastudy.juc.sync;

public class MyThreadSample extends Thread{

    private SampleSync sampleSync;

    public MyThreadSample(SampleSync sync){
        this.sampleSync = sync;
    }

    @Override
    public void run() {
        sampleSync.sync();
    }
}
