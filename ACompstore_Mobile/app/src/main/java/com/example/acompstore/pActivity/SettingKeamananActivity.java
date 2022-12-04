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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingKeamananActivity extends AppCompatActivity {

    TextView txtemail, txtintemail, txtpw;
    TextInputEditText txtintpw;
    Button btn_simpanpw, btn_batal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        TextView email, password;
        ImageButton btnBack;



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_keamanan);

        btnBack = findViewById(R.id.regal_btbackset);
        btnBack.setOnClickListener(view -> onBackPressed());

        email = findViewById(R.id.tam_email3);
        email.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getEmailPembeli());

        password = findViewById(R.id.tam_pw);
        password.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getPasswordPembeli());

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

        txtemail = view.findViewById(R.id.txt_alertemail1);
        txtintemail = view.findViewById(R.id.tam_alertemail1);
        txtintemail.setText(SaveAccount.readDataPembeli(SettingKeamananActivity.this).getEmailPembeli());

        txtpw = view.findViewById(R.id.txt_alertpw);
        txtintpw = view.findViewById(R.id.int_alertpw);

        btn_simpanpw = view.findViewById(R.id.btn_simpanpw);
        btn_simpanpw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceAcount service = Apiretro.getService().create(ServiceAcount.class);
                ModelPembeliAlamat mpa = SaveAccount.readDataPembeli(SettingKeamananActivity.this);
                Call<ResponsePos> simpan = service.setUbahKeamanan(mpa.getIdPembeli(), txtintpw.getText().toString());
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
                            ModelPembeliAlamat mpaskmn = SaveAccount.readDataPembeli(SettingKeamananActivity.this);
                            mpaskmn.setPasswordPembeli(txtintpw.getText().toString());
                            SaveAccount.writeDataPembeli(SettingKeamananActivity.this, mpaskmn);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePos> call, Throwable t) {
                        Toast.makeText(SettingKeamananActivity.this, "Server Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                onBackPressed();
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