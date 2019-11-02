package com.umg.iot.panico;

import com.umg.iot.models.Panico;

public interface PanicoRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void updatePanico(Panico panico);
}
