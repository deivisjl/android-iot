package com.umg.iot.temperature;

import com.umg.iot.models.Temperature;

public interface TemperaturePresenter {
    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();

    void onEventMainThread(TemperatureEvent event);
}
