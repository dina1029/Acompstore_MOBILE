package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.acompstore.R;
import com.example.acompstore.pFragment.HistoryFragment;

public class SplashScreenTransaksi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_transaksi);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                startActivity(new Intent(SplashScreenTransaksi.this, HistoryFragment.class));
                Intent inten = new Intent(SplashScreenTransaksi.this, HomeActivity.class);
                startActivity(inten);
                finish();
            }
        }, 5500);
    }
}