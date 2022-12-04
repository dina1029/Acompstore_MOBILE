package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponsePos;
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
    TextView nullintemail, nulltxtemail, nulltxtnama, nulltxtnotelp, nulltxtpw;
    TextInputEditText  nullintnama, nullintnotelp, nullintpw;
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

    private void alertGantiAkun() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingAkunActivity.this);
        View view = getLayoutInflater().inflate(R.layout.alert_change_akun, null);
        alert.setView(view);
        alertt = alert.create();
        alertt.show();

        nulltxtemail = view.findViewById(R.id.txt_alertemail);
        nullintemail = view.findViewById(R.id.tam_alertemail);
        nullintemail.setText(SaveAccount.readDataPembeli(SettingAkunActivity.this).getEmailPembeli());

        nulltxtnama = view.findViewById(R.id.txt_alertnama);
        nullintnama = view.findViewById(R.id.int_alertnama);

        nulltxtnotelp = view.findViewById(R.id.txt_alertnotelp);
        nullintnotelp = view.findViewById(R.id.int_alertnotelp);

        nulltxtpw = view.findViewById(R.id.txt_alertpw);
        nullintpw = view.findViewById(R.id.int_alertpw);

        nullbtsimpan = view.findViewById(R.id.btn_simpanakun);
        nullbtsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                ModelPembeliAlamat mpa = SaveAccount.readDataPembeli(SettingAkunActivity.this);
                Call<ResponsePos> simpan = service.setUbahAkun(mpa.getIdPembeli(), nullintnama.getText().toString(), nullintnotelp.getText().toString(), nullintpw.getText().toString());
                simpan.enqueue(new Callback<ResponsePos>() {
                    @Override
                    public void onResponse(Call<ResponsePos> call, Response<ResponsePos> response) {
                        if (response.body().getKode()==1){
                            View view = getLayoutInflater().inflate(R.layout.toast_ubah, null);
                            view.findViewById(R.id.toast_ubah);
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setView(view);
                            toast.show();
                            toast.setGravity(Gravity.TOP| Gravity.LEFT, 280 ,1600);
                            ModelPembeliAlamat mpa2 = SaveAccount.readDataPembeli(SettingAkunActivity.this);
                            mpa2.setNamaPembeli(nullintnama.getText().toString());
                            mpa2.setNoHPPembeli(nullintnotelp.getText().toString());
                            SaveAccount.writeDataPembeli(SettingAkunActivity.this, mpa2);
                        } else {
                            Toast.makeText(SettingAkunActivity.this, "Password Tidak Sesuai", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePos> call, Throwable t) {
                        Toast.makeText(SettingAkunActivity.this, "Server Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                onBackPressed();
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