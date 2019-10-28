package com.umg.iot.panico;

public interface PanicoRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
}
