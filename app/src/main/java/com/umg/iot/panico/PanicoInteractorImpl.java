package com.umg.iot.panico;

import com.umg.iot.models.Panico;

public class PanicoInteractorImpl implements PanicoInteractor {
    private PanicoRepository repository;

    public PanicoInteractorImpl() {
        this.repository = new PanicoRepositoryImpl();
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
    public void updatePanico(Panico panico) {
        repository.updatePanico(panico);
    }
}
