package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public interface GasPresenter {
    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();
    void updateGas(Gas gas);

    void onEventMainThread(GasEvent event);
}
