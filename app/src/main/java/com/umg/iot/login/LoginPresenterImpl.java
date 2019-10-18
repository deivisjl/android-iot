package com.umg.iot.login;

import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

import static com.umg.iot.login.LoginEvent.onLoginError;
import static com.umg.iot.login.LoginEvent.onLoginSuccess;

public class LoginPresenterImpl implements LoginPresenter {

    private EventBus eventBus;
    private LoginView view;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.view = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstace();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForSession() {
        if(view !=null){
            view.disableInputs();
            view.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(view !=null){
            view.disableInputs();
            view.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getType()){
            case onLoginSuccess:
                onLoginSuccess(event.getEmail());
                break;
            case onLoginError:
                onLoginError(event.getMessage());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedSession();
                break;
        }
    }

    private void onLoginError(String message) {
        if (view != null){
            view.hideProgress();
            view.enabledInputs();
            view.loginError(message);
        }
    }

    private void onLoginSuccess(String email) {
        if (view != null){
            view.setCurrentUser(email);
            view.hideProgress();
            view.navigateToMainScreen();
        }
    }

    private void onFailedSession() {
        if (view != null){
            view.hideProgress();
            view.enabledInputs();
        }
    }
}
