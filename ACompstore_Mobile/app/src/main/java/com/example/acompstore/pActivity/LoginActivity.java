package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityLoginBinding;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pResponse.ResponsePost;
import com.example.acompstore.pService.ServiceRegisterLogin;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    TextInputEditText password, email;
    AlertDialog alert;
    boolean checknull = false;
    AppCompatButton nullbterror;
    TextView nullEmail;
    SharedPreferences shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        password = binding.loginPassword;
        email = binding.loginEmail;
        binding.loginBtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginData();
            }
        });
        binding.loginTvdaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    private void loginData() {
        String head="";
        if(binding.loginEmail.getText().toString().isEmpty() && binding.loginPassword.getText().toString().isEmpty()){
            head = "Masukkan Email dan Password";
            checknull = false;
        }else if (binding.loginEmail.getText().toString().isEmpty()){
            head = "Masukkan Email";
            checknull = false;
        }else if(binding.loginPassword.getText().toString().isEmpty()){
            head = "Masukkan Password";
            checknull = false;
        } else{
            email = binding.loginEmail;
            password = binding.loginPassword;
            ServiceRegisterLogin service = Apiretro.getService().create(ServiceRegisterLogin.class);
            Call<ResponsePost> simpan = service.setLogin(email.getText().toString(), password.getText().toString());
            simpan.enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    byte kode = response.body().getKode();
                    if (kode == 1) {
                        shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putBoolean("status", true);
                        editor.commit();
                        Toast.makeText(LoginActivity.this, "Berhasil Login", Toast.LENGTH_SHORT).show();
                    } else if (kode == 0) {
                        Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error server : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            checknull = true;
        }
        if (checknull==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.error_null_dialog, null);
            builder.setView(view);
            alert = builder.create();
            alert.show();
            nullbterror = view.findViewById(R.id.nulldialog_btclose);
            nullEmail = view.findViewById(R.id.nulldialog_header);
            nullbterror.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                }
            });
            nullEmail.setText(head);
            checknull = false;
        }
    }
}