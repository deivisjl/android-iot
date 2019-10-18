package com.umg.iot.lib;

public class GreenRobotEventBus implements EventBus{
    org.greenrobot.eventbus.EventBus eventBus;

    public GreenRobotEventBus() {
        this.eventBus =  org.greenrobot.eventbus.EventBus.getDefault();
    }

    private static class SingletonHolder{
        private static final GreenRobotEventBus INSTANCE = new GreenRobotEventBus();
    }
    public static GreenRobotEventBus getInstace(){
        return SingletonHolder.INSTANCE;
    }
    @Override
    public void register(Object subscriber) {
        eventBus.register(subscriber);
    }

    @Override
    public void unregister(Object subscriber) {
        eventBus.unregister(subscriber);
    }

    @Override
    public void post(Object event) {
        eventBus.post(event);
    }
}
