package com.example.akbar.smartcity.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.akbar.smartcity.R;

public class SplashScreen extends AppCompatActivity {

    private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        iv = findViewById(R.id.imagesplash);
        Animation myanim = AnimationUtils.loadAnimation()

    }
}
