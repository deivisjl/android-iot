package com.umg.iot.intruder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Intruder;

public class IntruderRepositoryImpl implements IntruderRepository{

    private FirebaseApi helper;
    private ChildEventListener listener;

    public IntruderRepositoryImpl() {
        helper = FirebaseApi.getInstance();
    }

    @Override
    public void subscribe() {
        if(listener == null)
        {
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Intruder intruder = dataSnapshot.getValue(Intruder.class);
                    postEvent(IntruderEvent.onIntruderAdded, intruder, null);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Intruder intruder = dataSnapshot.getValue(Intruder.class);
                    postEvent(IntruderEvent.onIntruderUpdated, intruder, null);
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
        helper.getIntruderDatabaseReference().addChildEventListener(listener);
    }

    private void postEvent(int type, Intruder intruder, String msg) {
        IntruderEvent event = new IntruderEvent();
        event.setType(type);
        event.setIntruder(intruder);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }

    @Override
    public void unsubscribe() {
        if(listener != null)
        {
            helper.getIntruderDatabaseReference().removeEventListener(listener);
        }
    }

    @Override
    public void destroyListener() {
        listener = null;
    }
}
