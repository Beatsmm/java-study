package com.example.javastudy.designMode.srp;

public interface GoodUserService {

    void login(String username, String password);
    void register(String email, String username, String password);
}
