package com.umg.iot.panico;

import android.view.View;

import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Panico;

import org.greenrobot.eventbus.Subscribe;

public class PanicoPresenterImpl implements PanicoPresenter {

    private PanicoView view;
    private EventBus eventBus;
    private PanicoInteractor interactor;

    public PanicoPresenterImpl(PanicoView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstace();
        this.interactor = new PanicoInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void subScribe() {
        interactor.subscribe();
    }

    @Override
    public void unSubcribe() {
        interactor.unsubscribe();
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void updatePanico(Panico panico) {
        interactor.updatePanico(panico);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PanicoEvent event) {
        switch (event.getType()){
            case PanicoEvent.onPanicoAdded:
                    onPanicoAdd(event.getPanico());
                break;
                case PanicoEvent.onPanicoUpdated:
                    onPanicoUpdate(event.getPanico());
                    break;
            case PanicoEvent.onPanicoSuccessUpdated:
                onPanicoSuccesUpdated();
                break;

        }
    }

    private void onPanicoSuccesUpdated() {
        if(view != null){
            view.onSuccesChanged();
        }
    }

    private void onPanicoUpdate(Panico panico) {
        if(view != null){
            view.onPanicoUpdated(panico);
        }
    }

    private void onPanicoAdd(Panico panico) {
        if(view != null){
            view.onPanicoAdded(panico);
        }
    }
}
