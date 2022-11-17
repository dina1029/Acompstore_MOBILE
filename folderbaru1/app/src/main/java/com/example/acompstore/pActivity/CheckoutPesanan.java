package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityCheckoutPesananBinding;

public class CheckoutPesanan extends AppCompatActivity {

    private ActivityCheckoutPesananBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_checkout_pesanan);


    }
}