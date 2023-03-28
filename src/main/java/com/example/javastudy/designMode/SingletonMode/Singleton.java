package com.example.javastudy.designMode.SingletonMode;

public class Singleton {

    String name = "";

    private Singleton(){

    }

    private static volatile Singleton instance = null;

    public static Singleton getInstance(){
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void printName(){
        System.out.println("my name is " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
