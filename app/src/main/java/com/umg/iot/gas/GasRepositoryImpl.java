package com.umg.iot.gas;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.umg.iot.api.FirebaseApi;
import com.umg.iot.lib.EventBus;
import com.umg.iot.lib.GreenRobotEventBus;
import com.umg.iot.models.Gas;

import java.util.HashMap;
import java.util.Map;

public class GasRepositoryImpl implements GasRepository {

    private FirebaseApi helper;
    private ChildEventListener listener;

    public GasRepositoryImpl() {
        this.helper = FirebaseApi.getInstance();
    }

    @Override
    public void subscribe() {
        if(listener == null){
            listener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Gas gas = dataSnapshot.getValue(Gas.class);
                    postEvent(GasEvent.onGasAdded, gas, null);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Gas gas = dataSnapshot.getValue(Gas.class);
                    postEvent(GasEvent.onGasUpdated, gas, null);
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
        helper.getSmockDatabaseReference().addChildEventListener(listener);
    }

    private void postEvent(int type, Gas gas, String msg) {
        GasEvent event = new GasEvent();

        event.setType(type);
        event.setGas(gas);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }

    @Override
    public void unsubscribe() {
        if(listener != null)
        {
            helper.getSmockDatabaseReference().removeEventListener(listener);
        }
    }

    @Override
    public void destroyListener() {
        listener = null;
    }

    @Override
    public void updateGas(Gas gas) {
        DatabaseReference ref = helper.getSmockDatabaseReference();
        DatabaseReference objRef = ref.child(gas.getId());

        Map<String,Object> updates = new HashMap<String, Object>();
        updates.put("Estado",1);
        objRef.updateChildren(updates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                if(databaseError == null){
                    postEvent(GasEvent.onGasSuccessUpdated,null);
                }
            }
        });
    }

    private void postEvent(int type, String msg){
        GasEvent event = new GasEvent();
        event.setType(type);
        event.setMessage(msg);

        EventBus eventBus = GreenRobotEventBus.getInstace();
        eventBus.post(event);
    }
}
