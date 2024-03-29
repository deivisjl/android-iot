package com.umg.iot.temperature;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Temperature;

import java.util.HashMap;
import java.util.Map;

public class TemperatureRepositoryImpl implements TemperatureRepository {

    private FirebaseApi helper;
    private ChildEventListener listener;

    public TemperatureRepositoryImpl() {
        helper = FirebaseApi.getInstance();
    }

    @Override
    public void subscribe() {
        if(listener == null)
        {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Temperature temperature = dataSnapshot.getValue(Temperature.class);
                    postEvent(TemperatureEvent.onTemperatureAdded, temperature, null);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Temperature temperature = dataSnapshot.getValue(Temperature.class);
                    postEvent(TemperatureEvent.onTemperatureChanged, temperature,null);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
        }
        helper.getTemperatureDatabaseReference().addChildEventListener(listener);
    }

    @Override
    public void unsubscribe() {
        if(listener != null)
        {
            helper.getTemperatureDatabaseReference().removeEventListener(listener);
        }
    }

    @Override
    public void destroyListener() {
        listener = null;
    }

    @Override
    public void updateTemperatura(Temperature temperature) {
        DatabaseReference ref = helper.getTemperatureDatabaseReference();
        DatabaseReference objRef = ref.child(temperature.getId());

        Map<String,Object> updates = new HashMap<String, Object>();
        updates.put("Estado",1);
        objRef.updateChildren(updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError == null){
                    postEvent(TemperatureEvent.onTemperatureSuccessUpdated,null);
                }
            }
        });
    }

    private void postEvent(int type, Temperature temperature, String msg) {
        TemperatureEvent event = new TemperatureEvent();
        event.setType(type);
        event.setTemperature(temperature);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }

    private void postEvent(int type, String msg){
        TemperatureEvent event = new TemperatureEvent();
        event.setType(type);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }
}
