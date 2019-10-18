package com.umg.iot.temperature;

public interface TemperaturePresenter {
    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();

    void onEventMainThread(TemperatureEvent event);
}
