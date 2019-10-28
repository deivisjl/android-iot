package com.umg.iot.panico;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Panico;
import com.umg.iot.models.Temperature;

public class PanicoRepositoryImpl implements PanicoRepository {
    private FirebaseApi helper;
    private ChildEventListener listener;

    public PanicoRepositoryImpl() {
        helper = FirebaseApi.getInstance();
    }

    @Override
    public void subscribe() {
        if(listener == null){
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Panico panico = dataSnapshot.getValue(Panico.class);
                    postEvent(PanicoEvent.onPanicoAdded, panico, null);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Panico panico = dataSnapshot.getValue(Panico.class);
                    postEvent(PanicoEvent.onPanicoUpdated, panico, null);
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

        helper.getPanicoDatabaseReference().addChildEventListener(listener);
    }

    private void postEvent(int type, Panico panico,String msg) {
        PanicoEvent event = new PanicoEvent();

        event.setType(type);
        event.setPanico(panico);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }

    @Override
    public void unsubscribe() {
        if(listener != null)
        {
            helper.getPanicoDatabaseReference().removeEventListener(listener);
        }
    }

    @Override
    public void destroyListener() {
        listener = null;
    }
}
