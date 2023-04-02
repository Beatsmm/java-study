package com.example.javastudy.designMode.lsp;

public class B extends Base{

    private A a = new A();

    public int fun1(int a, int b){
        return a+b;
    }

    public int fun2(int a, int b){
        return fun1(a, b) + 9;
    }

    public int fun3(int a, int b){
        return this.a.func1(a, b);
    }
}
