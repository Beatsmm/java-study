package com.example.javastudy.designMode.srp;

public interface BadUserService {

    void login(String username, String password);
    void register(String email, String username, String password);
    void logInfo(String msg);
    void sendEmail(String email);
}
