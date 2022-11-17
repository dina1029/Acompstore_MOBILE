package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.acompstore.R;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton btn_1, btn_2;
        Button btn1, btn2, btn3, btn4;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_1= (ImageButton) findViewById(R.id.btn_ker);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_2 = (ImageButton) findViewById(R.id.btn_cht);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wpurl = "https://wa.me/+6281333793691?text=Saya memiliki keluhan.....";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });

        btn1 = (Button) findViewById(R.id.btn_akun);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, SettingAkunActivity.class));
            }
        });

        btn2 = (Button) findViewById(R.id.btn_kmn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, SettingKeamananActivity.class));
            }
        });

        btn3 = (Button) findViewById(R.id.btn_as);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, SettingAlamatActivity.class));
            }
        });

        btn4 = (Button) findViewById(R.id.btn_logout);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(R.layout.activity_logout);
//        alertDialogBuilder.setPositiveButton(new AlertDialog.OnClickListener() {
//                public void onClick(AlertDialog dialog,int id) {
//                LoginActivity.this.finish();
//            }
//        });
//        alertDialogBuilder.setNegativeButton(new AlertDialog.Builder().OnClickListener() {
//                public void OnClick(AlertDialog dialog,int id) {
//                    dialog.cancel();
//                }
//        })
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}