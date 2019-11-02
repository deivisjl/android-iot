package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public interface IntruderPresenter {
    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();
    void updateIntruder(Intruder intruder);

    void onEventMainThread(IntruderEvent event);
}
