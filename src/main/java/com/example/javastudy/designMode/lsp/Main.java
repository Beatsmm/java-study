package com.example.javastudy.designMode.lsp;

public class Main {

    public static void main(String[] args) {
        A a = new A();
        System.out.println("11-3="+a.func1(11, 3));
        System.out.println("1-8="+a.func1(1, 8));

        System.out.println("-------------");

        B b = new B();
        //因为B类不再继承A类，
        System.out.println("11+3="+b.fun1(11, 3)); //这里本意是求出11+3
        System.out.println("1+8="+b.fun1(1, 8)); //1+8
        System.out.println("11+3+9="+b.fun2(11, 3));

        //使用组合仍然可以使用到A类相关方法
        System.out.println("11-3="+b.fun3(11, 3)); //这里本意是求出11-3

    }
}
