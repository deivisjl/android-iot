package com.umg.iot.gas;

import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Gas;

import org.greenrobot.eventbus.Subscribe;

public class GasPresenterImpl implements GasPresenter {
    private GasView view;
    private GasInteractor interactor;
    private EventBus eventBus;

    public GasPresenterImpl(GasView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstace();
        this.interactor = new GasInteractorImpl();
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
    public void updateGas(Gas gas) {
        interactor.updateGas(gas);
    }

    @Override
    @Subscribe
    public void onEventMainThread(GasEvent event) {
        switch (event.getType()){
            case GasEvent.onGasAdded:
                onGasAdd(event.getGas());
                break;
                case GasEvent.onGasUpdated:
                    onGasUpdate(event.getGas());
                    break;
            case GasEvent.onGasSuccessUpdated:
                onGasChanged();
                break;
        }
    }

    private void onGasChanged() {
        if(view != null){
            view.onSuccesChanged();
        }
    }

    private void onGasUpdate(Gas gas) {
        if(view != null){
            view.onGasUpdated(gas);
        }
    }

    private void onGasAdd(Gas gas) {
        if(view != null){
            view.onGasAdded(gas);
        }
    }
}
