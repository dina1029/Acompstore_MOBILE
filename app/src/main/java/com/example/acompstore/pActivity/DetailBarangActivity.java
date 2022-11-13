package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityDetailBarangBinding;
import com.example.acompstore.pAdapter.AdapterDetailKategori;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelKategori;
import com.example.acompstore.pResponse.ResponseGetKategori;
import com.example.acompstore.pService.ServiceDetailBarang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarangActivity extends AppCompatActivity implements AdapterItemClick{

    private ActivityDetailBarangBinding bind;
    String idBarang, nama, harga, deskripsi, terjual, gambar, stok, diskon;
    private List<ModelKategori> list;
    AdapterDetailKategori adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_detail_barang);

        Bundle extras = getIntent().getExtras();
        nama = extras.getString("nama", nama);
        harga = extras.getString("harga", harga);
        terjual = extras.getString("terjual", terjual);
        deskripsi = extras.getString("deskripsi", deskripsi);
        stok = extras.getString("stok", stok);
        gambar = extras.getString("gambar", gambar);
        bind.debarNama.setText(nama);
        new CurrencyModel(harga, bind.debarHarga);
        new CurrencyModel(harga, bind.debarTotalharga);
        bind.debarStok.setText(stok);
        bind.debarDescription.setText(deskripsi);
        ImageConvertModel icm = new ImageConvertModel(DetailBarangActivity.this, gambar, bind.debarImage);
        icm.ubahGambar();
        tampilKategori();
        bind.debarBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bind.debarMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int strjumlah = Integer.parseInt(bind.debarJumlahbarang.getText().toString());
                if (strjumlah-1 != 0){
                    int hasil = strjumlah - 1;
                    bind.debarJumlahbarang.setText(String.valueOf(hasil));
                    int harga1 = Integer.parseInt(harga);
                    int jumlah1 = Integer.parseInt(bind.debarJumlahbarang.getText().toString());
                    int total1 = harga1 * jumlah1;
                    new CurrencyModel(String.valueOf(total1), bind.debarTotalharga);
                }
            }
        });
        bind.debarPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int strjumlah = Integer.parseInt(bind.debarJumlahbarang.getText().toString());
                int hasil = strjumlah + 1;
                bind.debarJumlahbarang.setText(String.valueOf(hasil));
                int harga1 = Integer.parseInt(harga);
                int jumlah1 = Integer.parseInt(bind.debarJumlahbarang.getText().toString());
                int total1 = harga1 * jumlah1;
                new CurrencyModel(String.valueOf(total1), bind.debarTotalharga);
            }
        });
    }


    private void tampilKategori(){
        Bundle extras = getIntent().getExtras();
        idBarang = extras.getString("idBarang", idBarang);
        LinearLayoutManager layout = new LinearLayoutManager(DetailBarangActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mlist = bind.debarRckategori;
        mlist.setLayoutManager(layout);
        ServiceDetailBarang service  = Apiretro.getService().create(ServiceDetailBarang.class);
        Call<ResponseGetKategori> tampildata = service.getDetailKategori(idBarang);
        tampildata.enqueue(new Callback<ResponseGetKategori>() {
            @Override
            public void onResponse(Call<ResponseGetKategori> call, Response<ResponseGetKategori> response) {
                list = response.body().getData();
                adapter = new AdapterDetailKategori(DetailBarangActivity.this, list, DetailBarangActivity.this);
                bind.debarRckategori.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseGetKategori> call, Throwable t) {
                Toast.makeText(DetailBarangActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        adapter.setSelectedPosition(position);
        adapter.getSelectedPosition();
        harga = list.get(position).getHargaKategori();
        new CurrencyModel(harga, bind.debarHarga);
        stok = list.get(position).getStok();
        bind.debarStok.setText(list.get(position).getStok());
        int hasil = Integer.parseInt(harga) * Integer.parseInt(bind.debarJumlahbarang.getText().toString());
        new CurrencyModel(String.valueOf(hasil), bind.debarTotalharga);
        gambar = list.get(position).getGambar();
        ImageConvertModel icm = new ImageConvertModel(DetailBarangActivity.this, gambar, bind.debarImage);
        icm.ubahGambar();
    }
}