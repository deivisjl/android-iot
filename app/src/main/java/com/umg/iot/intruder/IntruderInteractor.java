package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public interface IntruderInteractor {
    void subscribe();
    void unsubscribe();
    void updateIntruder(Intruder intruder);
}
