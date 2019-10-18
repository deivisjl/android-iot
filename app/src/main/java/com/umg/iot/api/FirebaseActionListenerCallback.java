package com.umg.iot.api;

public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(String error);
}
