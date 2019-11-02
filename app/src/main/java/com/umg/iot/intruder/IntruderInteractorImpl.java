package com.umg.iot.intruder;

import com.umg.iot.models.Intruder;

public class IntruderInteractorImpl implements IntruderInteractor{
    private IntruderRepository repository;

    public IntruderInteractorImpl() {
        this.repository = new IntruderRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void updateIntruder(Intruder intruder) {
        repository.updateIntruder(intruder);
    }
}
