package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityUbahDataAkunBinding;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pModel.ModelPembeliAlamat;

public class UbahDataAkunActivity extends AppCompatActivity {

    private ActivityUbahDataAkunBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_ubah_data_akun);
        ModelPembeliAlamat modelPembeli = SaveAccount.readDataPembeli(this);
        bind.akEmail.setText(modelPembeli.getEmailPembeli());
        bind.akNama.setText(modelPembeli.getNamaPembeli());
        bind.akNotelp.setText(modelPembeli.getNoHPPembeli());

        bind.akBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}