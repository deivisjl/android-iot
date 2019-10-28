package com.umg.iot.panico;
import com.umg.iot.panico.PanicoEvent;

public interface PanicoPresenter {

    void onCreate();
    void subScribe();
    void unSubcribe();
    void onDestroy();

    void onEventMainThread(PanicoEvent event);
}
