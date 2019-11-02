package com.umg.iot.panico;
import com.umg.iot.models.Panico;
import com.umg.iot.panico.PanicoEvent;

public interface PanicoPresenter {

    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();
    void updatePanico(Panico panico);

    void onEventMainThread(PanicoEvent event);
}
