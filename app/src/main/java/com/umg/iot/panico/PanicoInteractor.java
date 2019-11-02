package com.umg.iot.panico;

import com.umg.iot.models.Panico;

public interface PanicoInteractor {
    void subscribe();
    void unsubscribe();
    void updatePanico(Panico panico);
}
