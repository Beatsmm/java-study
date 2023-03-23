package com.example.javastudy.designMode.proxyMode.easy;

public class Main {

    public static void main(String[] args) {
        new LoginServiceProxy(new LoginService()).proxy("messi","123456");
    }
}
