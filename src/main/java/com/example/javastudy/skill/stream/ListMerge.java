package com.example.javastudy.skill.stream;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListMerge {
    public static void main(String[] args) {

        // 根据id合并多个List,可能一个List中的属性只有id和name,另一个只有id和age,现在根据id把他们都合并起来
        User user1 = new User(1, "迪迦奥特曼", null);
        User user2 = new User(2, "泰罗奥特曼", null);
        User user3 = new User(3, "赛文奥特曼", null);
        List<User> idAndName = Lists.newArrayList(user1, user2, user3);

        User user4 = new User(1,null,1000);
        User user5 = new User(2,null,1001);
        User user6 = new User(3,null,1002);
        List<User> idAndAge = Lists.newArrayList(user4, user5, user6);

        List<User> res = Stream.of(idAndName, idAndAge).flatMap(List::stream)
                .collect(Collectors.toMap(User::getId, Function.identity(), User::merge))
                .values()
                .stream()
                .collect(Collectors.toList());
        System.out.println(res);
    }
}
