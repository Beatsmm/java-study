package com.example.javastudy.skill.guava;

import com.google.common.collect.Range;
import com.google.common.collect.TreeRangeMap;

public class GuavaRangeMap {

    public static void main(String[] args) {
        // 对年龄进行分类
        String people = getRank(25);
        System.out.println(people); // 青年
        // 用RangeMap范围分类
        TreeRangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closedOpen(0, 18),"未成年");
        rangeMap.put(Range.closed(18, 30),"青年");
        rangeMap.put(Range.openClosed(30, 60),"壮年");
        rangeMap.put(Range.atLeast(60),"老年");
        System.out.println(rangeMap.get(15)); // 未成年
        System.out.println(rangeMap.get(18)); // 青年
        System.out.println(rangeMap.get(25)); // 青年
        System.out.println(rangeMap.get(30)); // 青年
        System.out.println(rangeMap.get(35)); // 壮年
        System.out.println(rangeMap.get(60)); // 老年
        System.out.println(rangeMap.get(65)); // 老年
        rangeMap.remove(Range.closed(0, 18));
        System.out.println(rangeMap.get(15));
    }

    private static String getRank(int age){
        if (0 <= age && age < 18){
            return "未成年";
        }else if (18 <= age && age <= 30){
            return "青年";
        }else if (30 < age && age <= 60){
            return "壮年";
        } else if (age > 60) {
            return "老年";
        }
        return "未知";
    }
}
