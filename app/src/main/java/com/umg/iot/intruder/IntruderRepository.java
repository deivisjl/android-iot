package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public interface IntruderRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void updateIntruder(Intruder intruder);
}
