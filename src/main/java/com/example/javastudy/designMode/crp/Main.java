package com.example.javastudy.designMode.crp;

public class Main {

    public static void main(String[] args) {
        GasoLineCar gasoLineCar = new GasoLineCar();
        gasoLineCar.setColor(new Black());
        gasoLineCar.move();

        ElectricCar electricCar = new ElectricCar();
        electricCar.setColor(new White());
        electricCar.move();
    }

}
