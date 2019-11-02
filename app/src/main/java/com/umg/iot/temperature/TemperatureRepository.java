package com.umg.iot.temperature;

import com.umg.iot.models.Temperature;

public interface TemperatureRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void updateTemperatura(Temperature temperature);
}
