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
import com.example.acompstore.databinding.ActivitySettingKeamananBinding;
import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePostAkun;
import com.example.acompstore.pService.ServiceAcount;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingKeamananActivity extends AppCompatActivity {

    TextView txtemail, txtnama, txtintemail, txtpw;
    TextInputEditText txtintpwlama, txtintpwbaru;
    Button btn_simpanpw, btn_batal;
    private ActivitySettingKeamananBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView email, nama, password;
        ImageButton btnBack;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_keamanan);

        btnBack = findViewById(R.id.regal_btbackset);
        btnBack.setOnClickListener(view -> onBackPressed());

        email = findViewById(R.id.tam_email3);
        email.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getEmailPembeli());

        nama = findViewById(R.id.tam_Nama3);
        nama.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getNamaPembeli());

        password = findViewById(R.id.tam_pw);
        password.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getNoHPPembeli());

        btn_simpanpw = findViewById(R.id.btn_ubahkmn);
        btn_simpanpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertGantiPassword();
            }
        });
    }

    private void alertGantiPassword() {
        AlertDialog.Builder alert = new AlertDialog.Builder(SettingKeamananActivity.this);
        View view = getLayoutInflater().inflate(R.layout.alert_change_keamanan, null);
        alert.setView(view);
        AlertDialog alertt = alert.create();
        alertt.show();

        txtpw = view.findViewById(R.id.txt_alertpw);
        txtintpwlama = view.findViewById(R.id.int_alertpwlama);
        txtintpwbaru = view.findViewById(R.id.int_alertpwbaru);

        btn_simpanpw = view.findViewById(R.id.btn_simpanpw);
        btn_simpanpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(txtintpwbaru.getText());
                System.out.println(txtintpwlama.getText());
                if (txtintpwbaru.getText().toString().equals("") || txtintpwlama.getText().toString().equals("")){
                    view = getLayoutInflater().inflate(R.layout.toast_datakosong, null);
                    view.findViewById(R.id.toast_datakosong1);
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(view);
                    toast.show();
                    toast.setGravity(Gravity.TOP| Gravity.CENTER, 0, 10);
                }else{
                    ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                    ModelPembeliAlamat mpa = SaveAccount.readDataPembeli(SettingKeamananActivity.this);
                    Call<ResponsePostAkun> simpan = service.setUbahKeamanan(mpa.getIdPembeli(), txtintpwlama.getText().toString(), txtintpwbaru.getText().toString());
                    simpan.enqueue(new Callback<ResponsePostAkun>() {
                        @Override
                        public void onResponse(Call<ResponsePostAkun> call, Response<ResponsePostAkun> response) {
                            if (response.body().getKode()==200){
                                View view = getLayoutInflater().inflate(R.layout.toast_ubah, null);
                                view.findViewById(R.id.toast_ubah);
                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(view);
                                toast.show();
                                toast.setGravity(Gravity.TOP| Gravity.CENTER, 0 ,1600);
                                ModelPembeliAlamat mpaskmn = response.body().getData();
                                SaveAccount.writeDataPembeli(SettingKeamananActivity.this, mpaskmn);
                                onBackPressed();
                            }else if (response.body().getKode()==402){
                                View view = getLayoutInflater().inflate(R.layout.toast_pwlamaerror, null);
                                view.findViewById(R.id.toast_pwlamaerror1);
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
                                toast.setGravity(Gravity.TOP | Gravity.CENTER, 250, 1600);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsePostAkun> call, Throwable t) {
                            Toast.makeText(SettingKeamananActivity.this, "Server Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btn_batal = view.findViewById(R.id.btn_batalpw);
        btn_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertt.cancel();
            }
        });
    }
}