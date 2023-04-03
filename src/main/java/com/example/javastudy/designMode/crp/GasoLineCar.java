package com.example.javastudy.designMode.crp;

public class GasoLineCar {


    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    public void move() {
        color.color();
        System.out.println("汽油汽车");
    }
}
