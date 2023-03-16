package com.example.javastudy.designMode.stateMode;

public class Approved implements State{
    @Override
    public String doSomething() {
        return "合同已通过";
    }
}
