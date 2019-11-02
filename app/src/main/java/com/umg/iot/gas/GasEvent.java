package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public class GasEvent {

    public static final int onGasAdded = 1;
    public static final int onGasUpdated = 2;
    public static final int onGasSuccessUpdated = 3;

    private int type;
    private String message;
    private Gas gas;

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

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }

}
