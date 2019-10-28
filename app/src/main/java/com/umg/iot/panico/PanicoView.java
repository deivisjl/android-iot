package com.umg.iot.panico;

import com.umg.iot.models.Panico;

public interface PanicoView {
    void onPanicoAdded(Panico panico);
    void onPanicoUpdated(Panico panico);
}
