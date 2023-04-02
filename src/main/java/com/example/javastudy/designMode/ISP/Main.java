package com.example.javastudy.designMode.ISP;


public class Main {

    public static void main(String[] args) {
        Staff staff = new HrImpl();
        staff.clockIn("hr");
        Hr hr = new HrImpl();
        hr.recruit();
    }
}
