package com.example.javastudy.designMode.chainMode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


/**
 * 模拟场景
 * 1、用户姓名不能为空
 * 2、用户年龄不能小于0不能大于60
 * 3、性别只能是男或者女
 */

/**
 * 总结：
 * 1、单一职责，可以对发起操作和执行操作的类解耦
 * 2、开闭原则，不需要修改，新增其他处理类
 * 场景
 * 按照顺序执行多个处理者，可以考虑使用
 */
public class Test {

    public static void main(String[] args) {
        CheckUser userCheck = getUserCheck();
        List<User> user = getUser();
        userCheck.handleCheck(user);

    }

    public static CheckUser getUserCheck(){
        // 校验姓名
        CheckName checkName = new CheckName();
        // 校验年龄
        CheckAge checkAge = new CheckAge();
        // 校验性别
        GenderCheck genderCheck = new GenderCheck();
        // 设置责任链顺序
        checkName.setCheckUser(checkAge);
        checkAge.setCheckUser(genderCheck);
        return checkName;
    }

    public static List<User> getUser(){
        List<User> result = new ArrayList<>();
        User u1 = new User("张三","男",10);
        User u2 = new User("李四","人妖",11);
        User u3 = new User("王武","女",102);
        User u4 = new User("","男",10);
        User u5 = new User("奥特曼","男",20);
        result.add(u1);
        result.add(u2);
        result.add(u3);
        result.add(u4);
        result.add(u5);
        return result;
    }

}
