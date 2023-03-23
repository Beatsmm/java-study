package com.example.javastudy.designMode.proxyMode.easy;

public class LoginServiceProxy {

    private LoginService loginService;

    public LoginServiceProxy(LoginService loginService) {
        this.loginService = loginService;
    }

    public void proxy(String username,String password){
        loginService.loginByPassword("messi","123456");
        System.out.println("登陆成功短信通知");
    }
}
