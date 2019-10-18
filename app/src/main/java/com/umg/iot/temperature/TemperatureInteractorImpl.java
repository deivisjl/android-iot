package com.umg.iot.temperature;

public class TemperatureInteractorImpl implements TemperatureInteractor {

    private TemperatureRepository repository;

    public TemperatureInteractorImpl() {
        this.repository = new TemperatureRepositoryImpl();
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
