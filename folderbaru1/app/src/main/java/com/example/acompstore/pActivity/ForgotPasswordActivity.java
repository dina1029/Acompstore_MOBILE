package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.acompstore.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    AppCompatButton bt_chat;
    ImageButton bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        bt_chat = findViewById(R.id.forgot_btchatadmin);
        bt_back = findViewById(R.id.forgot_btback);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bt_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wpurl = "https://wa.me/+6281333793691?text=Admin, saya ingin mereset password.";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });
    }
}