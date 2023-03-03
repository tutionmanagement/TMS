package com.example.tms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }
    public void toSignin(View v){
        Intent intent = new Intent(MainActivity.this, SigninActivity.class);
        startActivity(intent);
    }
    public void toSignup(View v){
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
