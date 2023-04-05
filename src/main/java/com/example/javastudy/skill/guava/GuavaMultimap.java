package com.example.javastudy.skill.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuavaMultimap {

    public static void main(String[] args) {
        // java 原始映射
        Map<String, List<Integer>> javaMapping = new HashMap<>();
        List<Integer> list = Lists.newArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        javaMapping.put("day", list);
        System.out.println(javaMapping);
        // Multimap
        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.put("day", 1);
        multimap.put("day", 2);
        multimap.put("day", 3);
        multimap.put("month", 3);
        System.out.println(multimap);

        //1、获取值的集合 可以创建ListMultimap、TreeMultimap、 HashMultimap
        List<Integer> listInt = multimap.get("day");
        // get方法会返回一个非null的集合,但是这个集合的内容可能是空的,看一下下面的例子
        List<Integer> testNullKey = multimap.get("nullKey");
        System.out.println(testNullKey); // []

        //2、操作get后的集合
        List<Integer> listDay = multimap.get("day");
        listDay.remove(0);
        List<Integer> listMonth = multimap.get("month");
        listMonth.add(12);
        System.out.println(multimap); // {month=[3, 12], day=[2, 3]}

        //3、转化为Map
        Map<String, Collection<Integer>> reverseMap = multimap.asMap();
        for (String key : reverseMap.keySet()) {
            System.out.println(key+" : "+reverseMap.get(key));
        }
        reverseMap.get("day").add(20);
        System.out.println(reverseMap);

        //4、数量问题
        System.out.println(multimap.size()); //5
        System.out.println(multimap.entries().size());//5
        for (Map.Entry<String, Integer> entry : multimap.entries()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }
}
