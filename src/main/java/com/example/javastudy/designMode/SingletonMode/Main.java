package com.example.javastudy.designMode.SingletonMode;

public class Main {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        instance.setName("xuhaoyu");
        instance.printName();
        Singleton instance1 = Singleton.getInstance();
        instance1.setName("xuhaoyu");
        instance1.printName();

        if (instance == instance1){
            System.out.println("创建的是同一个实例");
        }else {
            System.out.println("创建的不是同一个实例");
        }
    }
}
