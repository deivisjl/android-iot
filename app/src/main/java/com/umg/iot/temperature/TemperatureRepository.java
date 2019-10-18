package com.umg.iot.temperature;

public interface TemperatureRepository {
    void subscribe();
    void unsubscribe();
    void destroyListener();
}
