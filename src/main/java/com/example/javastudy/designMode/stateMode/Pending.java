package com.example.javastudy.designMode.stateMode;

public class Pending implements State {
    @Override
    public String doSomething() {
        return "合同创建待处理";
    }
}
