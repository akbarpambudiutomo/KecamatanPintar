package com.example.akbar.smartcity.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.akbar.smartcity.R;
import com.example.akbar.smartcity.util.SessionManager;
import com.example.akbar.smartcity.view.fragment.AddPhotoFragment;
import com.example.akbar.smartcity.view.fragment.HomeFragment;
import com.example.akbar.smartcity.view.fragment.ProfileFragment;

import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {

    private static final int TIME_LIMIT = 1800;
    private static long backPressed;

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private static final String SELECTED_ITEM = "arg_selected_item";
    SessionManager sessionManager;
    HashMap<String, String> map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sessionManager = new SessionManager(getApplicationContext());

        map = sessionManager.getDetailLogin();

        mBottomNav = findViewById(R.id.navigation);
        mBottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        MenuItem selectedItem;
        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt(SELECTED_ITEM, 0);
            selectedItem = mBottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = mBottomNav.getMenu().getItem(0);
        }
        selectFragment(selectedItem);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(SELECTED_ITEM, mSelectedItem);
        super.onSaveInstanceState(outState);
    }

    private void selectFragment(MenuItem item){
        Fragment frag = null;
        switch (item.getItemId()){
            case  R.id.menu_home:
                frag = new HomeFragment();
                Bundle hBundle = new Bundle();
                hBundle.putString("nama_lengkap", map.get(SessionManager.KEY_NAMA));
                hBundle.putString("email", map.get(SessionManager.KEY_EMAIL));
                frag.setArguments(hBundle);
                break;
            case R.id.menu_add:
                frag = new AddPhotoFragment();
                break;
            case R.id.menu_profile:
                frag = new ProfileFragment();
                Bundle mBundle = new Bundle();
                frag.setArguments(mBundle);
                break;
        }
        loadFragment(frag);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();

        }

    }

    private void logout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Anda yakin ingin logout ?");
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface arg0, int arg1){
                sessionManager.logout();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.logout){
            logout();
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        } else {
            Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }


}
