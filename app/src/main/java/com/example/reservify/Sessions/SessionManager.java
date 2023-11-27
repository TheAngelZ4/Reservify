package com.example.reservify.Sessions;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String PREF_NAME = "MyappPref";
    private static final String KEY_USER_ID = "user_id";

    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;

    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setKeyUserId(int userId){
        editor.putInt(KEY_USER_ID, userId);
        editor.apply();
    }

    public int getUserId(){
        return sharedPreferences.getInt(KEY_USER_ID, -1);
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }

}
