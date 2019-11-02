package com.umg.iot.temperature;

import com.umg.iot.models.Temperature;

public class TemperatureEvent {
    public static final int onTemperatureAdded = 1;
    public static final int onTemperatureChanged = 2;
    public static final int onTemperatureSuccessUpdated = 3;

    private int type;
    private String message;
    private Temperature temperature;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }
}
