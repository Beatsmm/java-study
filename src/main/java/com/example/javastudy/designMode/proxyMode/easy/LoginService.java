package com.example.javastudy.designMode.proxyMode.easy;

import org.apache.commons.lang3.StringUtils;

public class LoginService {

    public void loginByPassword(String userName,String password){
        if (StringUtils.equals("messi",userName) && StringUtils.equals("123456",password)){
            System.out.println("登陆成功");
        }
    }

}
