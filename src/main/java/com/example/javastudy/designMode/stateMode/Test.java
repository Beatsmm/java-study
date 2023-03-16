package com.example.javastudy.designMode.stateMode;

public class Test {

    public static void main(String[] args) {
        Context ctx = new Context();
        ctx.setState(new Pending());
        System.out.println(ctx.weatherMessage());
    }
}
