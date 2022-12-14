package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivitySettingAlamatBinding;
import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelKotaProvinsi;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pPengiriman.ServicePengiriman;
import com.example.acompstore.pPengiriman.pCity.ModelKota;
import com.example.acompstore.pPengiriman.pCity.ResponseKota;
import com.example.acompstore.pPengiriman.pProvince.ModelProvinsi;
import com.example.acompstore.pPengiriman.pProvince.ResponseProvinsi;
import com.example.acompstore.pResponse.ResponseKotaProvinsi;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePostAkun;
import com.example.acompstore.pService.ServiceAcount;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingAlamatActivity extends AppCompatActivity {

    ImageButton btnBack;
    Button simpann, batall;
    TextInputEditText int1, int2, int3;
    private List<ModelProvinsi> listProvinsi;
    private List<ModelKota> listKota;
    int idProvinsi = 10;
    int idKota = 1;
    private ActivitySettingAlamatBinding bind;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_setting_alamat);
        btnBack = (ImageButton) findViewById(R.id.regal_btbackset);
        btnBack.setOnClickListener(view -> onBackPressed());

        ModelPembeliAlamat mpla = SaveAccount.readDataPembeli(SettingAlamatActivity.this);
        bind.setalAlamat.setText(mpla.getAddress());
        bind.setalKelura.setText(mpla.getKelurahan());
        bind.setalKec.setText(mpla.getKecamatan());
        bind.setalEmail.setText(mpla.getEmailPembeli());

        simpann = findViewById(R.id.btn_ubahalamat);
        simpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGantiAlamat();
            }
        });
        namaKotaProvinsi();
    }

    private void namaKotaProvinsi(){
        String idKota = SaveAccount.readDataPembeli(SettingAlamatActivity.this).getKota();
        ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
        Call<ResponseKotaProvinsi> getKotaProv = service.getKotaProv(idKota);
        getKotaProv.enqueue(new Callback<ResponseKotaProvinsi>() {
            @Override
            public void onResponse(Call<ResponseKotaProvinsi> call, Response<ResponseKotaProvinsi> response) {
                if (response.body().getKode()==200){
                    ModelKotaProvinsi mkp = response.body().getData();
                    bind.setalKab.setText(mkp.getCity_name());
                    bind.setalProv.setText(mkp.getProvince());

                }else{
                    new ErrorDialog(SettingAlamatActivity.this, "Data Tidak Ada", "Alamat tidak terdaftar");
                }
            }

            @Override
            public void onFailure(Call<ResponseKotaProvinsi> call, Throwable t) {
                Toast.makeText(SettingAlamatActivity.this, "Server error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void alertGantiAlamat() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingAlamatActivity.this);
        View view = getLayoutInflater().inflate(R.layout.alert_ubah_alamat, null);
        alert.setView(view);
        AlertDialog alertt = alert.create();
        alertt.show();
        TextInputEditText kecamatan, kelurahan, address;
        kecamatan = view.findViewById(R.id.int_alertkec2);
        kelurahan = view.findViewById(R.id.int_alertkelurahan);
        address = view.findViewById(R.id.int_alertal2);

        ModelPembeliAlamat mpla = SaveAccount.readDataPembeli(SettingAlamatActivity.this);
        kecamatan.setText(mpla.getKecamatan());
        kelurahan.setText(mpla.getKelurahan());
        address.setText(mpla.getAddress());

        Spinner sp_prov, sp_kab;
        TextInputEditText password;
        sp_prov = view.findViewById(R.id.tam_alertprov);
        sp_kab = view.findViewById(R.id.int_alertkab2);
        password = view.findViewById(R.id.int_alertpassword);
        tampilProvinsi(sp_prov);
        tampilKota(sp_kab);
        sp_prov.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idProvinsi = Integer.parseInt(listProvinsi.get(sp_prov.getSelectedItemPosition()).getProvince_id());
                tampilKota(sp_kab);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_kab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idKota = Integer.parseInt(listKota.get(sp_kab.getSelectedItemPosition()).getCity_id());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        simpann = view.findViewById(R.id.btn_simpanal);
        simpann.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(kecamatan.getText().toString().isEmpty() || kelurahan.getText().toString().isEmpty() || address.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    view = getLayoutInflater().inflate(R.layout.toast_datakosong, null);
                    view.findViewById(R.id.toast_datakosong1);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(view);
                    toast.show();
                    toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 10);
                }else{
                    ModelPembeliAlamat mpla = SaveAccount.readDataPembeli(SettingAlamatActivity.this);
                    ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                    Call<ResponsePostAkun> ubahAlamat = service.setUbahAlamat(mpla.getIdPembeli(), password.getText().toString(), mpla.getIdAlamat(),
                            String.valueOf(idProvinsi), String.valueOf(idKota), kecamatan.getText().toString(), kelurahan.getText().toString(), address.getText().toString());
                    ubahAlamat.enqueue(new Callback<ResponsePostAkun>() {
                        @Override
                        public void onResponse(Call<ResponsePostAkun> call, Response<ResponsePostAkun> response) {
                            int kode = response.body().getKode();
                            if (kode == 200){
                                View view = getLayoutInflater().inflate(R.layout.toast_ubah, null);
                                view.findViewById(R.id.toast_ubah);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0 ,1600);
                                SaveAccount.writeDataPembeli(SettingAlamatActivity.this, response.body().getData());
                                onBackPressed();
                            }else if(kode ==402){
                                View view = getLayoutInflater().inflate(R.layout.toast_pwerror, null);
                                view.findViewById(R.id.toast_pwerror1);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 10);
                            }else {
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
                            Toast.makeText(SettingAlamatActivity.this, "Server error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

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
    private void tampilProvinsi(Spinner sp) {
        ServicePengiriman service = Apiretro.getService().create(ServicePengiriman.class);
        Call<ResponseProvinsi> dataProvinsi = service.getProvinsi();
        dataProvinsi.enqueue(new Callback<ResponseProvinsi>() {
            @Override
            public void onResponse(Call<ResponseProvinsi> call, Response<ResponseProvinsi> response) {
                listProvinsi = response.body().getRajaongkir().getResults();
//                Spinner sp = bind.regalProvinsi;
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

    private void tampilKota(Spinner sp) {
        ServicePengiriman service = Apiretro.getService().create(ServicePengiriman.class);
        Call<ResponseKota> dataKota = service.getKota(idProvinsi);
        dataKota.enqueue(new Callback<ResponseKota>() {
            @Override
            public void onResponse(Call<ResponseKota> call, Response<ResponseKota> response) {
                listKota = response.body().getRajaongkir().getResults();
//                Spinner sp = bind.regalKabupaten;
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
                Toast.makeText(SettingAlamatActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}