package com.umg.iot.login;

public interface LoginRepository {
    void signIn(String email, String password);
    void checkSession();
}
