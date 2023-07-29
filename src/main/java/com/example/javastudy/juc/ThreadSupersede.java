package com.example.javastudy.juc;

public class ThreadSupersede {

    public static void main(String[] args) {
        Execute execute = new Execute(1, 50);
        new Thread(() -> {
            execute.print("A", 1, 2);
        }).start();
        new Thread(() -> {
            execute.print("B", 2, 1);
        }).start();
    }
}

class Execute{

    private int flag;

    private final int loopNum;

    public Execute(int flag, int loopNum) {
        this.flag = flag;
        this.loopNum = loopNum;
    }

    public void print(String letter, int currFlag, int nextFlag){
        for (int i = 0; i < loopNum; i++) {
            synchronized (this){
                while(flag != currFlag){
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(letter);
                flag = nextFlag;
                this.notifyAll();
            }
        }
    }
}
