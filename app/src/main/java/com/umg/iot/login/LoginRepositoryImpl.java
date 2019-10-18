package com.umg.iot.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.umg.iot.api.FirebaseActionListenerCallback;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseApi helper;

    public LoginRepositoryImpl(){
        helper = FirebaseApi.getInstance();
    }
    @Override
    public void signIn(String email, String password) {
        helper.getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String email = helper.getAuthUserEmail();
                        postEvent(LoginEvent.onLoginSuccess,email);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        postEvent(LoginEvent.onLoginError,e.getLocalizedMessage(), 0);
                    }
                });
    }

    @Override
    public void checkSession() {
        helper.checkForSession(new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                String email = helper.getAuthUserEmail();
                postEvent(LoginEvent.onLoginSuccess,email);
            }

            @Override
            public void onError(String error) {
                postEvent(LoginEvent.onFailedToRecoverSession,null,0);
            }
        });
    }

    private void postEvent(int type, String email) {
        postEvent(type,null,email);
    }
    private void postEvent(int type, String msg, int e) {
        postEvent(type,msg,null);
    }
    private void postEvent(int type, String error, String email) {
        LoginEvent event = new LoginEvent();
        event.setType(type);
        event.setMessage(error);
        event.setEmail(email);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }
}
