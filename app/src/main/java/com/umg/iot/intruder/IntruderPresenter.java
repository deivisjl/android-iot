package com.umg.iot.intruder;

public interface IntruderPresenter {
    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();

    void onEventMainThread(IntruderEvent event);
}
