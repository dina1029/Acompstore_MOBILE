package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingAlamatActivity extends AppCompatActivity {

    TextView email1, kab, kec, alamat;
    ImageButton btnBack;
    Button simpann, batall;
    TextView txt1, txt1_2, txt2, txt3, txt4;
    TextInputEditText int1, int2, int3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_alamat);
        btnBack = (ImageButton) findViewById(R.id.regal_btbackset);
        btnBack.setOnClickListener(view -> onBackPressed());

        email1 = findViewById(R.id.tam_email2);
        email1.setText(SaveAccount.readDataPembeli(SettingAlamatActivity.this).getEmailPembeli());

        kab = findViewById(R.id.tam_kab);
        kab.setText(SaveAccount.readDataPembeli(SettingAlamatActivity.this).getKota());

        kec = findViewById(R.id.tam_kec);
        kec.setText(SaveAccount.readDataPembeli(SettingAlamatActivity.this).getKecamatan());

        alamat = findViewById(R.id.tam_al);
        alamat.setText(SaveAccount.readDataPembeli(SettingAlamatActivity.this).getAddress());

        simpann = findViewById(R.id.btn_ubahalamat);
        simpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGantiAlamat();
            }
        });
    }

    private void alertGantiAlamat() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingAlamatActivity.this);
        View view = getLayoutInflater().inflate(R.layout.alert_ubah_alamat, null);
        alert.setView(view);
        AlertDialog alertt = alert.create();
        alertt.show();

        txt1 = view.findViewById(R.id.txt_alertemail2);
        txt1_2 = view.findViewById(R.id.tam_alertemail2);
        txt1_2.setText(SaveAccount.readDataPembeli(SettingAlamatActivity.this).getEmailPembeli());

        txt2 = view.findViewById(R.id.txt_alertkab2);
        int1 = view.findViewById(R.id.int_alertkab2);

        txt3 = view.findViewById(R.id.txt_alertkec2);
        int2 = view.findViewById(R.id.int_alertkec2);

        txt4 = view.findViewById(R.id.txt_alertal2);
        int3 = view.findViewById(R.id.int_alertal2);

        simpann = view.findViewById(R.id.btn_simpanal);
        simpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                ModelPembeliAlamat mpa = SaveAccount.readDataPembeli(SettingAlamatActivity.this);
                Call<ResponsePos> simpan = service.setUbahAlamat(mpa.getIdPembeli(), int1.getText().toString(), int2.getText().toString(), int3.getText().toString());
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
                            ModelPembeliAlamat mpasal = SaveAccount.readDataPembeli(SettingAlamatActivity.this);
                            mpasal.setKota(int1.getText().toString());
                            mpasal.setKecamatan(int2.getText().toString());
                            mpasal.setAddress(int3.getText().toString());
                            SaveAccount.writeDataPembeli(SettingAlamatActivity.this, mpasal);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePos> call, Throwable t) {
                        Toast.makeText(SettingAlamatActivity.this, "Server Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                onBackPressed();
            }
        });
        batall = view.findViewById(R.id.btn_batalal);
        batall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertt.cancel();
            }
        });
    }
}