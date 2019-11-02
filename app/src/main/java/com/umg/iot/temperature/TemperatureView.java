package com.umg.iot.temperature;

import com.umg.iot.models.Temperature;

public interface TemperatureView {
    void onTemperatureAdd(Temperature temperature);
    void onTemperatureChanged(Temperature temperature);
    void onSuccesChanged();
}
