package com.umg.iot.temperature;

import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Temperature;

import org.greenrobot.eventbus.Subscribe;

public class TemperaturePresenterImpl implements TemperaturePresenter {

    private TemperatureView view;
    private EventBus eventBus;
    private  TemperatureInteractor interactor;

    public TemperaturePresenterImpl(TemperatureView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstace();
        this.interactor = new TemperatureInteractorImpl();
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
    @Subscribe
    public void onEventMainThread(TemperatureEvent event) {
        switch (event.getType())
        {
            case TemperatureEvent.onTemperatureAdded:
                    temperatureAdded(event.getTemperature());
                    break;
            case TemperatureEvent.onTemperatureChanged:
                   temperatureChanged(event.getTemperature());
                   break;
        }
    }

    private void temperatureChanged(Temperature temperature) {
        if(view != null)
        {
            view.onTemperatureChanged(temperature);
        }
    }

    private void temperatureAdded(Temperature temperature) {
        if(view != null)
        {
            view.onTemperatureAdd(temperature);
        }
    }
}
