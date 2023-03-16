package com.example.javastudy.designMode.stateMode;

public class Cancel implements State {
    @Override
    public String doSomething() {
        return "合同被取消";
    }
}
