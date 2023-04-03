package com.example.javastudy.designMode.lod;

import java.util.List;

public class Arthas {

    private List<Interface> interfaces;

    public Arthas(List<Interface> interfaces) {
        this.interfaces = interfaces;
    }

    public void count(){
        System.out.println("这个项目一共的接口数量是:"+interfaces.size());
    }
}
