package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pPengiriman.pProvince.ModelProvinsi;
import com.example.acompstore.pPengiriman.pCity.ModelKota;
import com.example.acompstore.pPengiriman.pCity.ResponseKota;
import com.example.acompstore.pPengiriman.pProvince.ResponseProvinsi;
import com.example.acompstore.pPengiriman.ServicePengiriman;
import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityRegisterAlamatBinding;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponsePostPembeli;
import com.example.acompstore.pService.ServiceRegisterLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterAlamatActivity extends AppCompatActivity {

    private ActivityRegisterAlamatBinding bind;
    String nama, pass, phone, email;
    SharedPreferences shared;
    boolean checknull = false;
    private List<ModelPembeliAlamat> list;
    private List<ModelProvinsi> listProvinsi;
    private List<ModelKota> listKota;
    int idProvinsi = 1;
    int idKota = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register_alamat);
        tampilProvinsi();
        tampilKota();
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

        bind.regalProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idProvinsi = Integer.parseInt(listProvinsi.get(bind.regalProvinsi.getSelectedItemPosition()).getProvince_id());
                tampilKota();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bind.regalKabupaten.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idKota = Integer.parseInt(listKota.get(bind.regalKabupaten.getSelectedItemPosition()).getCity_id());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void tampilProvinsi() {
        ServicePengiriman service = Apiretro.getService().create(ServicePengiriman.class);
        Call<ResponseProvinsi> dataProvinsi = service.getProvinsi();
        dataProvinsi.enqueue(new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                listProvinsi = response.body().getRajaongkir().getResults();
                Spinner sp = bind.regalProvinsi;
                if (listProvinsi != null && listProvinsi.size() > 0) {
                    String[] listdata = new String[listProvinsi.size()];

                    for (int i = 0; i < listProvinsi.size(); i++) {
                        listdata[i] = listProvinsi.get(i).getProvince();
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listdata);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        sp.setAdapter(spinnerArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseProvinsi> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void tampilKota() {
        ServicePengiriman service = Apiretro.getService().create(ServicePengiriman.class);
        Call<ResponseKota> dataKota = service.getKota(idProvinsi);
        dataKota.enqueue(new Callback<ResponseKota>() {
            @Override
            public void onResponse(Call<ResponseKota> call, Response<ResponseKota> response) {
                listKota = response.body().getRajaongkir().getResults();
                Spinner sp = bind.regalKabupaten;
                if (listKota != null && listKota.size() > 0) {
                    String[] listdata = new String[listKota.size()];

                    for (int i = 0; i < listKota.size(); i++) {
                        listdata[i] = listKota.get(i).getType() + " " + listKota.get(i).getCity_name();
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, listdata);
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                        sp.setAdapter(spinnerArrayAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseKota> call, Throwable t) {
                Toast.makeText(RegisterAlamatActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
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
        if (bind.regalKelurahan.getText().toString().isEmpty() || bind.regalKecamatan.getText().toString().isEmpty()
                || bind.regalDetail.getText().toString().isEmpty() || bind.regalKelurahan.getText().toString().isEmpty()) {
            head = "Lengkapi Data Kosong";
            body = "Mohon lengkapi data alamat anda";
            checknull = false;
        } else if (bind.regalCheck.isChecked() == false) {
            head = "Centang Persetujuan";
            body = "Centang persetujuan jika anda menyutujui peraturan aplikasi ini";
            checknull = false;
        } else {
            ServiceRegisterLogin service = Apiretro.getService().create(ServiceRegisterLogin.class);
            Call<ResponsePostPembeli> simpan = service.registerPembeli(email, nama, phone, pass,
                    String.valueOf(idProvinsi), String.valueOf(idKota), bind.regalKecamatan.getText().toString(),
                    bind.regalKelurahan.getText().toString(), bind.regalDetail.getText().toString());
            simpan.enqueue(new Callback<ResponsePostPembeli>() {
                @Override
                public void onResponse(Call<ResponsePostPembeli> call, Response<ResponsePostPembeli> response) {
                    byte kode = response.body().getKode();
                    list = response.body().getData();
                    if (kode == 1) {
                        shared = getSharedPreferences("myapp-data", MODE_PRIVATE);
                        SharedPreferences.Editor editor = shared.edit();
                        editor.putBoolean("status", true);
                        editor.commit();
                        Intent intent = new Intent(RegisterAlamatActivity.this, SuccessRegisterActivity.class);
                        ModelPembeliAlamat dataPembeli = list.get(0);
                        SaveAccount.writeDataPembeli(RegisterAlamatActivity.this, dataPembeli);
                        startActivity(intent);
                        ActivityCompat.finishAffinity(RegisterAlamatActivity.this);
                    } else if (kode == 0) {
                        Toast.makeText(RegisterAlamatActivity.this, "Gagal Register" + response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterAlamatActivity.this, "Register Gagal" + response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsePostPembeli> call, Throwable t) {
                    Toast.makeText(RegisterAlamatActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
            checknull = true;
        }if (checknull==false){
            new ErrorDialog(RegisterAlamatActivity.this, head, body);
            checknull = false;
        }
    }
}