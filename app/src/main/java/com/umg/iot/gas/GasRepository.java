package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public interface GasRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void updateGas(Gas gas);
}
