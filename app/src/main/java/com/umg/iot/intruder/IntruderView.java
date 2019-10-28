package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public interface IntruderView {
    void onIntruderAdd(Intruder intruder);
    void onIntruderUpdated(Intruder intruder);
}
