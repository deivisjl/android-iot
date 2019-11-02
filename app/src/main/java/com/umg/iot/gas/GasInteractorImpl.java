package com.umg.iot.gas;

import com.umg.iot.models.Gas;

public class GasInteractorImpl implements GasInteractor {
    private GasRepository repository;

    public GasInteractorImpl() {
        this.repository = new GasRepositoryImpl();
    }

    @Override
    public void subscribe() {
        this.repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        this.repository.unsubscribe();
    }

    @Override
    public void updateGas(Gas gas) {
        this.repository.updateGas(gas);
    }
}
