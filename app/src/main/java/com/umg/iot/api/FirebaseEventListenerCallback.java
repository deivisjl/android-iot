package com.umg.iot.api;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface FirebaseEventListenerCallback {
    void onChildAdded(DataSnapshot dataSnapshot);
    void onCanceled(DatabaseError databaseError);
}
