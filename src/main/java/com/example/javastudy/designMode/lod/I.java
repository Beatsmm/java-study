package com.example.javastudy.designMode.lod;

public class I {

    private Medium medium;

    public I(Medium medium){
        this.medium = medium;
    }


    private void rentHouse(){
        medium.sign();
    }
}
