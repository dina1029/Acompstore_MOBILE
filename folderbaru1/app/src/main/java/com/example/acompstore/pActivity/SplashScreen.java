package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.acompstore.R;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
                if(shared.getBoolean("status", false)==true){
                    Intent home = new Intent(SplashScreen.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }else{
                    Intent home = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        }, 2000);
    }
}