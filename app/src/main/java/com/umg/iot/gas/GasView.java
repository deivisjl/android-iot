package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public interface GasView {
    void onGasAdded(Gas gas);
    void onGasUpdated(Gas gas);
    void onSuccesChanged();
}
