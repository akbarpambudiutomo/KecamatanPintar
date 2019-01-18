package com.example.akbar.smartcity.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.akbar.smartcity.view.activity.LoginActivity;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private static final String KEY_EMAIL = "email";
    private static final String KEY_NAMA = "nama_lengkap";
    private static final String is_login = "loginstatus";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context)
    {
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLogin(String email, String nama)
    {
        editor.putBoolean(is_login, true);
        editor.putString(KEY_EMAIL, email);
        editor.putString(KEY_NAMA, nama);
        editor.commit();
    }

    public HashMap getDetailLogin()
    {
        HashMap<String ,String > map = new HashMap<>();
        map.put(KEY_NAMA,sp.getString(KEY_NAMA, null));
        map.put(KEY_NAMA,sp.getString(KEY_EMAIL, null));
        return map;
    }

    public void checkLogin()
    {
        if(!this.Login())
        {
            Intent login = new Intent(_context, LoginActivity.class);
            login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            login.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(login);
        }
    }

    public Boolean Login()
    {
        return sp.getBoolean(is_login,false);
    }
}
