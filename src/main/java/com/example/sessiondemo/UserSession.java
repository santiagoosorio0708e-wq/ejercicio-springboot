package com.example.sessiondemo;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserSession {

    private SessionUser user;

    public void login(String username) {
        this.user = new SessionUser(username);
    }

    public SessionUser getUser() {
        return user;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        this.user = null;
    }
}
