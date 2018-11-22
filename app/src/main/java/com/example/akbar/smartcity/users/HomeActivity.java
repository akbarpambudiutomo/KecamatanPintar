package com.example.akbar.smartcity.users;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.akbar.smartcity.R;
import com.example.akbar.smartcity.users.fragment.AddPhotoFragment;
import com.example.akbar.smartcity.users.fragment.HomeFragment;
import com.example.akbar.smartcity.users.fragment.ProfileFragment;


public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView mBottomNav;
    private int mSelectedItem;
    private static final String SELECTED_ITEM = "arg_selected_item";

    SharedPreferences sharedpreferences;
    public static final String TAG_ID = "id_user";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_NOHP = "nohp";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_USERNAME = "username";
    public final static String TAG_FOTO = "foto";
    String idx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
                hBundle.putString(TAG_ID, idx);
                frag.setArguments(hBundle);
                break;
            case R.id.menu_add:
                frag = new AddPhotoFragment();
                break;
            case R.id.menu_profile:
                frag = new ProfileFragment();
                Bundle mBundle = new Bundle();
                mBundle.putString(TAG_ID, idx);
                frag.setArguments(mBundle);
                break;
        }
        loadFragment(frag);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
