package com.umg.iot.login;

public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForSession();
    void validateLogin(String email, String password);

    void onEventMainThread(LoginEvent event);
}
