package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityHomeSearchBinding;
import com.example.acompstore.pAdapter.AdapterHomeBarang;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pFragment.HomeFragment;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pResponse.ResponseGetBarang;
import com.example.acompstore.pService.ServiceHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeSearchActivity extends AppCompatActivity implements AdapterItemClick {

    private ActivityHomeSearchBinding bind;
    private List<ModelHomeBarang> list = new ArrayList<>();
    private AdapterHomeBarang adapterBarang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_home_search);

        bind.homesearchEtcari.requestFocus();

        bind.homesearchBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bind.homesearchEtcari.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    cari_barang();
                    return true;
                }
                return false;
            }
        });
    }
    private void cari_barang(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        bind.homesearchRcbarang.setLayoutManager(gridLayoutManager);
        bind.homesearchRcbarang.setNestedScrollingEnabled(false);
        ServiceHome service = Apiretro.getService().create(ServiceHome.class);
        Call<ResponseGetBarang> tampildata = service.getCariBarang(bind.homesearchEtcari.getText().toString());
        tampildata.enqueue(new Callback<ResponseGetBarang>() {
            @Override
            public void onResponse(Call<ResponseGetBarang> call, Response<ResponseGetBarang> response) {
                if(response.body().getKode()==1) {
                    list = response.body().getData();
                    adapterBarang = new AdapterHomeBarang(getApplicationContext(), list, HomeSearchActivity.this);
                    bind.homesearchRcbarang.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                    bind.homesearchViewnull.setVisibility(View.GONE);
                    bind.homesearchRcbarang.setVisibility(View.VISIBLE);
                }else{
                    list = new ArrayList<>();
                    adapterBarang = new AdapterHomeBarang(getApplicationContext(), list, HomeSearchActivity.this);
                    bind.homesearchRcbarang.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                    bind.homesearchViewnull.setVisibility(View.VISIBLE);
                    bind.homesearchImgilust.setImageResource(R.drawable.null_data);
                    bind.homesearchText.setText("Data yang anda cari tidak ada");
                    bind.homesearchRcbarang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBarang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailBarangActivity.class);
        intent.putExtra("idBarang", list.get(position).getIdBarang());
        intent.putExtra("terjual", list.get(position).getTerjual());
        intent.putExtra("deskripsi", list.get(position).getDeskripsiBarang());
        intent.putExtra("gambar", list.get(position).getGambar());
        intent.putExtra("idKategori", list.get(position).getIdKategori());
        intent.putExtra("nama", list.get(position).getNamaBarang());
        intent.putExtra("harga", list.get(position).getHargaKategori());
        intent.putExtra("stok", list.get(position).getStok());
        intent.putExtra("diskon", list.get(position).getDiskon());
        startActivity(intent);
    }
}