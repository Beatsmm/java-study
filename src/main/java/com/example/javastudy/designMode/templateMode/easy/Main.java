package com.example.javastudy.designMode.templateMode.easy;

public class Main {

    public static void main(String[] args) {
        AbstractSetting setting1 = new LocalCache();
        System.out.println("test = " + setting1.getSetting("test"));
        System.out.println("test = " + setting1.getSetting("test"));

        AbstractSetting setting2 = new RedisCache();
        System.out.println("autosave = " + setting2.getSetting("autosave"));
        System.out.println("autosave = " + setting2.getSetting("autosave"));
    }
}
