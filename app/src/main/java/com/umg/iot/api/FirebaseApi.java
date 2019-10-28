package com.umg.iot.api;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseApi {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseApi INSTANCE;

    public static final String TEMPERATURE_PATH = "TEMPERATURE";
    public static final String INTRUDER_PATH = "INTRUDER";
    public static final String PANIC_PATH = "PANIC";
    public static final String SMOCK_PATH = "SMOCK";
    public static final String ALERT_PATH = "ALERT";

    public FirebaseApi() {
        this.databaseReference = FirebaseDatabase.getInstance().getReference(ALERT_PATH);
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseApi getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final FirebaseApi INSTANCE = new FirebaseApi();
    }

    public DatabaseReference getDatabaseReference(){
        return databaseReference;
    }

    public FirebaseAuth getFirebaseAuth(){return this.firebaseAuth;}

    public DatabaseReference getTemperatureDatabaseReference(){
        return getDatabaseReference().child(TEMPERATURE_PATH);
    }

    public DatabaseReference getIntruderDatabaseReference(){
        return getDatabaseReference().child(INTRUDER_PATH);
    }

    public DatabaseReference getPanicoDatabaseReference(){
        return getDatabaseReference().child(PANIC_PATH);
    }

    public String getAuthUserEmail(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = null;
        if (user != null){
            email = user.getEmail();
        }
        return email;
    }

    public void logOut(){
        firebaseAuth.signOut();
    }

    public void checkForSession(FirebaseActionListenerCallback listenerCallback){
        if (firebaseAuth.getCurrentUser() != null){
            listenerCallback.onSuccess();
        }else{
            listenerCallback.onError(null);
        }
    }
    public String getAuthUserKey(){
        FirebaseUser userKey = firebaseAuth.getCurrentUser();
        String userUid = null;
        if(userKey != null){
            userUid = userKey.getUid();
        }
        return userUid;
    }

}
