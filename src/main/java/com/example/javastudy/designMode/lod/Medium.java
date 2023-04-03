package com.example.javastudy.designMode.lod;

public class Medium {

    private void findApartment(){
        System.out.println("寻找房源");
    }

    private void contact(){
        System.out.println("联系房东");
    }

    public void sign(){
        findApartment();
        contact();
        System.out.println("签订合同");
    }
}
