package com.example.sessiondemo;

public class SessionUser {
    private String username;
    private long loginTime;

    public SessionUser(String username) {
        this.username = username;
        this.loginTime = System.currentTimeMillis();
    }

    public String getUsername() {
        return username;
    }

    public long getLoginTime() {
        return loginTime;
    }
}
