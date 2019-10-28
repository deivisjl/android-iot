package com.umg.iot.intruder;

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
}
