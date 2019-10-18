package com.umg.iot.login;

public class LoginInteractorImpl implements  LoginInteractor {
    LoginRepository loginRepository;

    public LoginInteractorImpl() {
        loginRepository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginRepository.checkSession();
    }

    @Override
    public void doSignIn(String email, String password) {
        loginRepository.signIn(email, password);
    }
}
