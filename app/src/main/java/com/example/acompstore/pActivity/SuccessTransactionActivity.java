package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pPengiriman.PengirimanDialog;
import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivitySuccessTransactionBinding;

public class SuccessTransactionActivity extends AppCompatActivity{

    private ActivitySuccessTransactionBinding bind;
    PengirimanDialog ald;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_success_transaction);

    }
}