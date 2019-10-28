package com.umg.iot.intruder;

public interface IntruderRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
}
