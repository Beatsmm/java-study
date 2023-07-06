package com.example.javastudy.skill.enums;

public class Test {


    public static void main(String[] args) {
        String cat = Animal.valueOf("Cat").eat();
        System.out.println(cat);
    }
}
