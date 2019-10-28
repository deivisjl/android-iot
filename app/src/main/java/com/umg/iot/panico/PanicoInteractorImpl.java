package com.umg.iot.panico;

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
}
