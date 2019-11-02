package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public class IntruderEvent {
    public static final int onIntruderAdded = 1;
    public static final int onIntruderUpdated = 2;
    public static final int onIntruderSuccessUpdated = 3;

    private int type;
    private String message;
    private Intruder intruder;

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

    public Intruder getIntruder() {
        return intruder;
    }

    public void setIntruder(Intruder intruder) {
        this.intruder = intruder;
    }
}
