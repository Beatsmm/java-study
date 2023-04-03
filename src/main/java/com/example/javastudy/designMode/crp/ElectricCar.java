package com.example.javastudy.designMode.crp;

public class ElectricCar {


    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        color.color();
        System.out.println("电动汽车");
    }
}
