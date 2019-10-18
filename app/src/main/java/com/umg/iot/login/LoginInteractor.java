package com.umg.iot.login;

public interface LoginInteractor {
    void checkSession();
    void doSignIn(String email, String password);
}
