package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityRegisterBinding;
import com.example.acompstore.pAdditional.AlertErrorDialog;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding bind;
    AlertDialog alert;
    boolean checknull=false;
    AppCompatButton nullbterror;
    TextView nullEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register);
        bind.registerBtalamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkKosong();
            }
        });
        bind.registerBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void checkKosong(){
        String head = "";
        if (bind.registerEmail.getText().toString().isEmpty()){
            head = "Masukkan Email";
            checknull = false;
        }else if(isValidEmail(bind.registerEmail.getText().toString().trim())==false){
            head = "Email Tidak Valid";
            checknull = false;
        }else if(bind.registerName.getText().toString().isEmpty()){
            head = "Masukan Nama";
            checknull = false;
        }else if(bind.registerPhone.getText().toString().isEmpty()){
            head = "Masukkan Nomor Telepon";
            checknull = false;
        }
        else if(bind.registerPass.getText().toString().isEmpty()){
            head = "Masukkan Password";
            checknull = false;
        }else if(bind.registerConpass.getText().toString().isEmpty()){
            head = "Masukkan Konfirmasi Password";
            checknull = false;
        }else if(!bind.registerConpass.getText().toString().equals(bind.registerPass.getText().toString())){
            head = "Konfirmasi Password Tidak Sesuai";
            checknull = false;
        }else{
            Intent intent = new Intent(RegisterActivity.this, RegisterAlamatActivity.class);
            intent.putExtra("email",bind.registerEmail.getText().toString());
            intent.putExtra("nama",bind.registerName.getText().toString());
            intent.putExtra("phone", bind.registerPhone.getText().toString());
            intent.putExtra("pass", bind.registerPass.getText().toString());
            startActivity(intent);
            checknull = true;
        }
        if (checknull==false){
            String body = "Mohon lengkapi data anda dengan benar";
            new AlertErrorDialog(RegisterActivity.this, head, body);
            checknull = false;
        }
    }
    private boolean isValidEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        } else {
            return false;
        }
    }
}