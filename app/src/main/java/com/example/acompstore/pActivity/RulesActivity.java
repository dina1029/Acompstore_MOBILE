package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;

import com.example.acompstore.R;

public class RulesActivity extends AppCompatActivity {

    AppCompatButton btn_kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        btn_kembali = findViewById(R.id.rules_kembali);
        btn_kembali.setOnClickListener(view -> onBackPressed());
    }
}