package com.example.javastudy.designMode.ISP;

import javax.lang.model.element.PackageElement;

public class ProgrammerImpl implements Programmer,Staff {



    @Override
    public void coding() {
        System.out.println("写代码");
    }

    @Override
    public void clockIn(String name) {
        System.out.println(name + "打卡");
    }
}
