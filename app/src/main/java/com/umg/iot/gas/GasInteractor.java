package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public interface GasInteractor {
    void subscribe();
    void unsubscribe();
    void updateGas(Gas gas);
}
