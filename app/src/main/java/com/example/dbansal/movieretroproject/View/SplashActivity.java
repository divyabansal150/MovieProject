package com.example.dbansal.movieretroproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.dbansal.movieretroproject.R;

/**
 * Created by d.bansal on 27/1/18.
 */

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 3000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.splash_screen);

                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },SPLASH_DISPLAY_TIME);*/
        super.onCreate(savedInstanceState, persistentState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setContentView(R.layout.splash_screen);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        },SPLASH_DISPLAY_TIME);
    }
}
