package com.umg.iot.intruder;

import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Intruder;


import org.greenrobot.eventbus.Subscribe;

import static com.umg.iot.intruder.IntruderEvent.onIntruderAdded;

public class IntruderPresenterImpl implements IntruderPresenter {

    private  IntruderView view;
    private EventBus eventBus;
    private IntruderInteractor interactor;

    public IntruderPresenterImpl(IntruderView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstace();
        this.interactor = new IntruderInteractorImpl();
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
    public void updateIntruder(Intruder intruder) {
        interactor.updateIntruder(intruder);
    }

    @Override
    @Subscribe
    public void onEventMainThread(IntruderEvent event) {
        switch (event.getType())
        {
            case IntruderEvent.onIntruderAdded:
                    onIntruderAdded(event.getIntruder());
                break;
            case IntruderEvent.onIntruderUpdated:
                    onIntruderUpdate(event.getIntruder());
                break;
            case IntruderEvent.onIntruderSuccessUpdated:
                intruderUpdated();
                break;
        }
    }

    private void intruderUpdated() {
        if(view != null)
        {
            view.onSuccesChanged();
        }
    }

    private void onIntruderUpdate(Intruder intruder) {
        if(view != null)
        {
            view.onIntruderUpdated(intruder);
        }
    }

    private void onIntruderAdded(Intruder intruder) {
        if(view != null)
        {
            view.onIntruderAdd(intruder);
        }
    }
}
