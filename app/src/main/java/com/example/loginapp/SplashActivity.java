package com.example.loginapp;


import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Intent intent= new Intent (SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();

    }
}