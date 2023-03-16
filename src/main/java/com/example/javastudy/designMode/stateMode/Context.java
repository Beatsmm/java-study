package com.example.javastudy.designMode.stateMode;



public class Context {

    private State state;

    public void Context(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String weatherMessage(){
        return state.doSomething();
    }
}
