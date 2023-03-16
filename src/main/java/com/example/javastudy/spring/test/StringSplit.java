package com.example.javastudy.spring.test;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StringSplit {

    public static void main(String[] args) {
        Date date = new Date();
        Date truncate = DateUtils.truncate(date, Calendar.DATE);
        Date date1 = DateUtils.addDays(truncate, 1);
        date1.setTime(date1.getTime() - 1);
        System.out.println(truncate);
        System.out.println(date1);

        String str = "2023-03-16";
        java.sql.Date date2 = java.sql.Date.valueOf(str);
        System.out.println(date2);
    }
}
