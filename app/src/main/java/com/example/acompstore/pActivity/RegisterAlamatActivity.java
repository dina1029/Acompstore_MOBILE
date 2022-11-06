package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityRegisterAlamatBinding;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pResponse.ResponsePost;
import com.example.acompstore.pService.ServiceRegisterLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAlamatActivity extends AppCompatActivity {

    private ActivityRegisterAlamatBinding bind;
    String nama, pass, phone, email;
    AppCompatButton nullbterror;
    TextView nullEmail, nullDesk;
    AlertDialog alert;
    boolean checknull = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register_alamat);

        bind.regalBtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpanData();
            }
        });
        bind.regalBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void simpanData() {
        String head = "";
        String body = "";
        Bundle extras = getIntent().getExtras();
        email = extras.getString("email");
        nama = extras.getString("nama");
        phone = extras.getString("phone");
        pass = extras.getString("pass");
        if (bind.regalKota.getText().toString().isEmpty() || bind.regalKecamatan.getText().toString().isEmpty()
                || bind.regalAlamat.getText().toString().isEmpty()) {
            head = "Lengkapi Data Kosong";
            body = "Mohon lengkapi data alamat anda";
            checknull = false;
        }else if(bind.regalCheck.isChecked()==false){
            head = "Centang Persetujuan";
            body = "Centang persetujuan jika anda menyutujui peraturan aplikasi ini";
        }
        else{
            ServiceRegisterLogin service = Apiretro.getService().create(ServiceRegisterLogin.class);
            Call<ResponsePost> simpan = service.registerPembeli(email, nama, phone, pass,
                    bind.regalKota.getText().toString(), bind.regalKecamatan.getText().toString(),
                    bind.regalAlamat.getText().toString());
            simpan.enqueue(new Callback<ResponsePost>() {
                @Override
                public void onResponse(Call<ResponsePost> call, Response<ResponsePost> response) {
                    byte kode = response.body().getKode();
                    if (kode == 1) {
                        Toast.makeText(RegisterAlamatActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
                    } else if (kode == 0) {
                        Toast.makeText(RegisterAlamatActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsePost> call, Throwable t) {

                }
            });
            checknull = true;
        }if (checknull==false){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            View view = getLayoutInflater().inflate(R.layout.error_null_dialog, null);
            builder.setView(view);
            alert = builder.create();
            alert.show();
            nullbterror = view.findViewById(R.id.nulldialog_btclose);
            nullDesk = view.findViewById(R.id.nulldialog_body);
            nullEmail = view.findViewById(R.id.nulldialog_header);
            nullbterror.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alert.dismiss();
                }
            });
            nullEmail.setText(head);
            nullDesk.setText(body);
            checknull = false;
        }
    }
}