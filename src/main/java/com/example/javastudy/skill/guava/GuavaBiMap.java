package com.example.javastudy.skill.guava;

import com.example.javastudy.skill.optional.User;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;

public class GuavaBiMap {

    public static void main(String[] args) {
        // 原始map,如果想通过value查找key的值,没有简便方法只能使用循环
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("test1","test1Value");
        hashMap.put("test2","test2Value");
        hashMap.put("test3","test3Value");
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key).equals("test2Value")){
                System.out.println("找到了value=test2Value对应的key="+key);
            }
        }

        // BiMap
        HashBiMap<String, String> biMap = HashBiMap.create();
        biMap.put("zhangsan","China");
        biMap.put("lisi","UK");
        biMap.put("wangwu","USA");
        System.out.println(biMap.get("zhangsan"));
        BiMap<String, String> inverse = biMap.inverse();
        // 使用value获取key
        System.out.println(inverse.get("USA"));
        // 注意！！！ 如果我们对这个biMap进行了修改那么会修改原来的map
        inverse.put("USA", "zhaoliu");
        System.out.println(inverse);

        // BiMap 复杂对象
        HashBiMap<String, User> userMap = HashBiMap.create();
        userMap.put("user1", new User("zhangsan", 23));
        userMap.put("user2", new User("lisi", 24));
        userMap.put("user3", new User("wangwu", 25));
        BiMap<User, String> inverseUser = userMap.inverse();
        System.out.println(inverseUser);
        inverseUser.put(new User("wangwu", 25), "user4");
        System.out.println(userMap);

        // BiMap中的key和value都不允许重复
        // 会报错IllegalArgumentException
        HashBiMap<String, String> repeatKeyOrValue = HashBiMap.create();
        repeatKeyOrValue.put("key1", "value1");
//        repeatKeyOrValue.put("key2", "value1");
        // 如果非要把新的key映射到已有的value上,那么也可以使用forcePut方法强制替换掉原有的key
        repeatKeyOrValue.forcePut("key2", "value1");
        System.out.println(repeatKeyOrValue);
    }
}
