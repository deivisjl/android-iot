package com.umg.iot.lib;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";

    private static MySharedPreference mySharedPreference;
    private SharedPreferences sharedPreferences;

    public static MySharedPreference getInstance(Context context) {
        if (mySharedPreference == null) {
            mySharedPreference = new MySharedPreference(context);
        }
        return mySharedPreference;
    }

    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME,Context.MODE_PRIVATE);
    }

    public void saveData(String value) {
        sharedPreferences.edit().putString(EMAIL_KEY, value).commit();
    }

    public String getData() {
        if (sharedPreferences!= null) {
            return sharedPreferences.getString(EMAIL_KEY, "");
        }
        return "";
    }

    public void deleteData(){
        if(sharedPreferences != null){
            sharedPreferences.edit().clear();
        }
    }
}
