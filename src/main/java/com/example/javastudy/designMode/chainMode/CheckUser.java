package com.example.javastudy.designMode.chainMode;

import java.util.List;
import java.util.Objects;

public abstract class CheckUser {

    // 包含有自身的一个属性，作用主要是下一个对数据的处理者
    protected CheckUser checkUser;

    // 进行下一个处理
    public void setCheckUser(CheckUser checkUser){
        this.checkUser = checkUser;
    }

    // 此方法是业务层调用的方法，即业务调用此方法，把学生的集合做参数传过来
    public void handleCheck(List<User> userList){
        if (Objects.nonNull(userList) && !userList.isEmpty()){
            List<User> checkIsOk = checkUserAns(userList);
            if (Objects.nonNull(checkUser) && Objects.nonNull(checkIsOk) && !checkIsOk.isEmpty()){
                checkUser.handleCheck(checkIsOk);
            }
        }
    }

    protected abstract List<User> checkUserAns(List<User> user);

}
