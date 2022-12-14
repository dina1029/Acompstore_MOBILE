package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePostAkun;
import com.example.acompstore.pService.ServiceAcount;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingAkunActivity extends AppCompatActivity {

    TextView email, username, notelp;
    ImageButton btnBack;
    Button btn_gantiakun;
    AlertDialog alertt;
    TextView nullintemail, nulltxtemail, nulltxtnama, nulltxtnotelp, nulltxtpw, tampw;
    TextInputEditText nullintnama, nullintnotelp, nullintpw;
    Button nullbtsimpan, nullbtbtl;
    private List<ModelPembeliAlamat> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_akun);
        btnBack = (ImageButton) findViewById(R.id.regal_btbackset);
        btnBack.setOnClickListener(view -> onBackPressed());

        email = (TextView) findViewById(R.id.tam_email);
        email.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getEmailPembeli());

        username = (TextView) findViewById(R.id.tam_nama);
        username.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getNamaPembeli());

        notelp = (TextView) findViewById(R.id.tam_notelp);
        notelp.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getNoHPPembeli());

        btn_gantiakun = findViewById(R.id.btn_ubahakun);
        btn_gantiakun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGantiAkun();
            }
        });
    }

    private void inputgagal() {
        String usernamee = nullintnama.getText().toString();
        String nomorr = nullintnotelp.getText().toString();
        String paswordd = nullintpw.getText().toString();

        if (usernamee.trim().equals("")) {
            nullintnama.setError("Username Harus Diisi");
        } else if (nomorr.trim().equals("")) {
            nullintnotelp.setError("Nomor Telepon Harus Diisi");
        } else if (paswordd.trim().equals("")) {
            nullintpw.setError("Password Harus Diisi");
        }
    }


    private void alertGantiAkun() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingAkunActivity.this);
        View view = getLayoutInflater().inflate(R.layout.alert_change_account, null);
        alert.setView(view);
        alertt = alert.create();
        alertt.show();

        nulltxtemail = view.findViewById(R.id.txt_alertemail);
        nullintemail = view.findViewById(R.id.tam_alertemail);
        nullintemail.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getEmailPembeli());

        nulltxtnama = view.findViewById(R.id.txt_alertnama);
        nullintnama = view.findViewById(R.id.int_alertnama);
        nullintnama.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getNamaPembeli());

        nulltxtnotelp = view.findViewById(R.id.txt_alertnotelp);
        nullintnotelp = view.findViewById(R.id.int_alertnotelp);
        nullintnotelp.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getNoHPPembeli());

        nulltxtpw = view.findViewById(R.id.txt_alertpw);
        nullintpw = view.findViewById(R.id.int_alertpw);

        nullbtsimpan = view.findViewById(R.id.btn_simpanakun);


        nullbtsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamee = nullintnama.getText().toString();
                String nomorr = nullintnotelp.getText().toString();
                String paswordd = nullintpw.getText().toString();
                if (nullintnama.getText().toString().equals("") || nullintnotelp.getText().toString().equals("") || nullintpw.getText().toString().equals("")) {
                    inputgagal();
                    view = getLayoutInflater().inflate(R.layout.toast_datakosong, null);
                    view.findViewById(R.id.toast_datakosong1);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(view);
                    toast.show();
                    toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 10);
//                    Toast.makeText(SettingAkunActivity.this, "Mohon lengkapi data", Toast.LENGTH_SHORT).show();
//                    inputgagal();
//                    View view1 = getLayoutInflater().inflate(R.layout.toast_salahpassword, null);
//                    view1.findViewById(R.id.toast_salahpw);
//                    Toast toast = new Toast(getApplicationContext());
//                    toast.setDuration(Toast.LENGTH_LONG);
//                    toast.setView(view1);
//                    toast.show();
//                    toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 200);
                } else{
                    ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                    ModelPembeliAlamat mpa = SaveAccount.readDataPembeli(SettingAkunActivity.this);
                    Call<ResponsePostAkun> simpan = service.setUbahAkun(mpa.getIdPembeli(), nullintnama.getText().toString(),
                            nullintnotelp.getText().toString(), nullintpw.getText().toString());
                    simpan.enqueue(new Callback<ResponsePostAkun>() {
                        @Override
                        public void onResponse(Call<ResponsePostAkun> call, Response<ResponsePostAkun> response) {
                            if (response.body().getKode() == 200) {
                                View view = getLayoutInflater().inflate(R.layout.toast_ubah, null);
                                view.findViewById(R.id.toast_ubah);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1600);
                                ModelPembeliAlamat modelPembeliAlamat1 = response.body().getData();
                                SaveAccount.writeDataPembeli(SettingAkunActivity.this, modelPembeliAlamat1);

                                SharedPreferences shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                editor.putBoolean("homeId", true);
                                editor.commit();
                                onBackPressed();
                            } else if(response.body().getKode()==402){
                                View view = getLayoutInflater().inflate(R.layout.toast_pwerror, null);
                                view.findViewById(R.id.toast_pwerror1);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 10);
                            }else{
                                View view = getLayoutInflater().inflate(R.layout.toast_notupdate, null);
                                view.findViewById(R.id.toast_notupdate1);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 1600);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsePostAkun> call, Throwable t) {
                            Toast.makeText(SettingAkunActivity.this, "Server error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        nullbtbtl = view.findViewById(R.id.btn_batalakun);
        nullbtbtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertt.cancel();
            }
        });
    }
}