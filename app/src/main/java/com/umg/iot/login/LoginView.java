package com.umg.iot.login;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void enabledInputs();
    void disableInputs();

    void setCurrentUser(String email);
    void loginError(String error);
    void navigateToMainScreen();
}
