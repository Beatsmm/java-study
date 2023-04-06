package com.example.javastudy.skill.guava;

import com.example.javastudy.skill.optional.Goods;
import com.example.javastudy.skill.optional.User;
import com.google.common.collect.MutableClassToInstanceMap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ClassToInstanceMap {

    public static void main(String[] args) {
        // 实例Map
        MutableClassToInstanceMap<Object> instanceMap = MutableClassToInstanceMap.create();
        User user = new User("张三",18);
        Goods goods = new Goods("鼠标", new BigDecimal("100"));
        instanceMap.put(User.class, user);
        instanceMap.put(Goods.class, goods);
        User instanceUser = instanceMap.getInstance(User.class);
        if (instanceUser == user) System.out.println(true);


        MutableClassToInstanceMap<Map> classToInstanceMap = MutableClassToInstanceMap.create();
        HashMap<String, Object> hashMap = new HashMap<>();
        TreeMap<String, Object> treeMap = new TreeMap<>();
        ArrayList<Object> list = new ArrayList<>();
        classToInstanceMap.put(HashMap.class, hashMap);
        classToInstanceMap.put(TreeMap.class, treeMap);
//        classToInstanceMap.put(ArrayList.class, list); // 编译不通过


    }
}
