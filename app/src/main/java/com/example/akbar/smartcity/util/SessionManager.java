package com.example.akbar.smartcity.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.akbar.smartcity.view.activity.LoginActivity;

import java.util.HashMap;

import static android.content.Context.MODE_PRIVATE;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY_NAMA = "nama_lengkap";
    public static final String KEY_EMAIL = "email";
    private static final String is_login = "loginstatus";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private final String SHARE_NAME = "loginsession";

    public SessionManager (Context context)
    {
        sp = context.getSharedPreferences(SHARE_NAME, MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String email, String nama_lengkap)
    {
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAMA, nama_lengkap);
        editor.commit();
    }

    public HashMap getDetailLogin()
    {
        HashMap<String ,String > map = new HashMap<>();
        map.put(KEY_NAMA,sp.getString(KEY_NAMA, null));
        map.put(KEY_EMAIL,sp.getString(KEY_EMAIL, null));
        return map;
    }

    public void logout(){
        editor.clear();
        editor.commit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public Boolean Login()
    {
        return sp.getBoolean(is_login,false);
    }
}
