package com.umg.iot.panico;

import com.umg.iot.models.Panico;

public class PanicoEvent {

    public static final int onPanicoAdded = 1;
    public static final int onPanicoUpdated = 2;

    private int type;
    private String message;
    private Panico panico;

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

    public Panico getPanico() {
        return panico;
    }

    public void setPanico(Panico panico) {
        this.panico = panico;
    }


}
