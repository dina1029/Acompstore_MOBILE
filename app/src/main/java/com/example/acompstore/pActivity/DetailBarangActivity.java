package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityDetailBarangBinding;
import com.example.acompstore.pAdapter.AdapterDetailKategori;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelKategori;
import com.example.acompstore.pResponse.ResponseGetKategori;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pService.ServiceDetailBarang;
import com.example.acompstore.pService.ServiceKeranjang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBarangActivity extends AppCompatActivity implements AdapterItemClick{

    private ActivityDetailBarangBinding bind;
    String idBarang, nama, harga, deskripsi, terjual, gambar, stok, diskon, idKategori;
    private List<ModelKategori> list;
    AdapterDetailKategori adapter;
    private int hargaAsli=0;
    String idPembeli;

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
        idKategori = extras.getString("idKategori", idKategori);
        diskon = extras.getString("diskon", diskon);
        bind.debarNama.setText(nama);
        hargaAsli = Integer.parseInt(harga) - Integer.parseInt(diskon);
        new CurrencyModel(String.valueOf(hargaAsli), bind.debarHarga);
        new CurrencyModel(String.valueOf(hargaAsli), bind.debarTotalharga);
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
                    int harga1 = hargaAsli;
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
                int harga1 = hargaAsli;
                int jumlah1 = Integer.parseInt(bind.debarJumlahbarang.getText().toString());
                int total1 = harga1 * jumlah1;
                new CurrencyModel(String.valueOf(total1), bind.debarTotalharga);
            }
        });
        bind.debarBtcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Integer.parseInt(stok) == 0){
                    new ErrorDialog(DetailBarangActivity.this, "Stok Kosong"
                            , "Stok barang kosong, anda tidak bisa membeli produk tersebut");
                }
                else if (Integer.parseInt(bind.debarJumlahbarang.getText().toString()) > Integer.parseInt(stok)){
                    new ErrorDialog(DetailBarangActivity.this, "Jumlah Barang Melebihi Batas"
                            , "Masukkan jumlah barang kurang dari stok");
                }else{
                    idPembeli = SaveAccount.readDataPembeli(getApplicationContext()).getIdPembeli();
                    ServiceKeranjang service = Apiretro.getService().create(ServiceKeranjang.class);
                    Call<ResponsePosMessage> simpan = service.addToCart(idKategori, idPembeli,
                            bind.debarJumlahbarang.getText().toString());
                    simpan.enqueue(new Callback<ResponsePosMessage>() {
                        @Override
                        public void onResponse(Call<ResponsePosMessage> call, Response<ResponsePosMessage> response) {
                            if (response.body().getKode()==1){
                                Toast.makeText(DetailBarangActivity.this, "Barang telah dimasukkan keranjang"
                                , Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(DetailBarangActivity.this, response.body().getPesan()
                                        , Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsePosMessage> call, Throwable t) {
                            Toast.makeText(DetailBarangActivity.this, "Server Error : " + t.getMessage()
                                    , Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        bind.debarBtcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailBarangActivity.this, CartActivity.class));
            }
        });
        checkDiskon(diskon);
    }

    private void checkDiskon(String strDiskon){
        int mydiskon = Integer.parseInt(strDiskon);
        if (mydiskon==0){
            bind.debarViewdiskon.setVisibility(View.GONE);
        }else{
            bind.debarViewdiskon.setVisibility(View.VISIBLE);
            int cariDiskon = Integer.parseInt(diskon) * 100 / Integer.parseInt(harga);
            bind.debarDiskondidapat.setText(cariDiskon + "%");
            new CurrencyModel(harga, bind.debarHargaasli);
            new CurrencyModel(String.valueOf(hargaAsli), bind.debarHargadiskon);
            bind.debarHargaasli.setPaintFlags(bind.debarHargaasli.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
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
        hargaAsli = Integer.parseInt(list.get(position).getHargaKategori()) - Integer.parseInt(list.get(position).getDiskon());
        harga = String.valueOf(hargaAsli);
        new CurrencyModel(harga, bind.debarHarga);
        stok = list.get(position).getStok();
        bind.debarStok.setText(list.get(position).getStok());
        int hasil = Integer.parseInt(harga) * Integer.parseInt(bind.debarJumlahbarang.getText().toString());
        new CurrencyModel(String.valueOf(hasil), bind.debarTotalharga);
        gambar = list.get(position).getGambar();
        ImageConvertModel icm = new ImageConvertModel(DetailBarangActivity.this, gambar, bind.debarImage);
        icm.ubahGambar();
        idKategori = list.get(position).getIdKategori();
        diskon = list.get(position).getDiskon();
        checkDiskon(diskon);
    }
}