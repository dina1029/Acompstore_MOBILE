package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityCartBinding;
import com.example.acompstore.pAdapter.AdapterKeranjang;
import com.example.acompstore.pAdditional.ErrorDialog;
import com.example.acompstore.pAdditional.CartSaveProduct;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelKeranjang;
import com.example.acompstore.pResponse.ResponseGetKeranjang;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePosJumlah;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pService.ServiceKeranjang;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements AdapterKeranjang.AdapterCartClick {

    private ActivityCartBinding bind;
    private List<ModelKeranjang> list;
    private AdapterKeranjang adapterKeranjang;
    private List<ModelKeranjang> cobaList = new ArrayList<>();
    private int hasil = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        tampilkanKeranjang();

        bind.cvcartBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        hasil = 0;
        new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);

        bind.cvcartBtcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cobaList == null){
                    new ErrorDialog(getApplicationContext(), "Pilih Barang", "Pilh Barang Untuk Checkout");
                }else{
                    if (cobaList.isEmpty()){
                        new ErrorDialog(CartActivity.this, "Barang Belum Dipilih", "Centang barang yang ingin dibeli");
                    }else{
                        Intent intent = new Intent(CartActivity.this, CheckoutPesananActivity.class);
                        CartSaveProduct.writeListKeranjang(getApplicationContext(), cobaList);
                        startActivity(intent);
                    }
                }
            }
        });

    }

    private void checkKeranjang(){
        if (list.isEmpty() && list==null){
            bind.cvcartViewnull.setVisibility(View.VISIBLE);
        }else{
            bind.cvcartViewnull.setVisibility(View.GONE);
        }
    }

    String idPembeli;
    private void tampilkanKeranjang() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView mlist = bind.cvcartRccart;
        mlist.setLayoutManager(layoutManager);

        idPembeli = SaveAccount.readDataPembeli(getApplicationContext()).getIdPembeli();
        ServiceKeranjang service = Apiretro.getService().create(ServiceKeranjang.class);
        Call<ResponseGetKeranjang> ambil = service.getKeranjang(idPembeli);
        ambil.enqueue(new Callback<ResponseGetKeranjang>() {
            @Override
            public void onResponse(Call<ResponseGetKeranjang> call, Response<ResponseGetKeranjang> response) {
                byte kode = response.body().getKode();
                if (kode == 1) {
                    list = response.body().getData();
                    adapterKeranjang = new AdapterKeranjang(CartActivity.this, list, CartActivity.this);
                    bind.cvcartRccart.setAdapter(adapterKeranjang);
                    adapterKeranjang.notifyDataSetChanged();
                    bind.cvcartViewnull.setVisibility(View.GONE);
                } else {
                    bind.cvcartViewnull.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetKeranjang> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteClick(int pos) {
        int jumlahbarang = Integer.parseInt(list.get(pos).getHargaKategori()) - Integer.parseInt(list.get(pos).getDiskon());
        ServiceKeranjang service = Apiretro.getService().create(ServiceKeranjang.class);
        Call<ResponsePos> delete = service.deleteKeranjang(String.valueOf(list.get(pos).getIdKeranjang()));
        delete.enqueue(new Callback<ResponsePos>() {
            @Override
            public void onResponse(Call<ResponsePos> call, Response<ResponsePos> response) {
                if (response.body().getKode() == 1) {
                    if (list.get(pos).isSelectedCheck() == true) {
                        hasil = hasil - (Integer.parseInt(list.get(pos).getKeranjangJumlah()) * jumlahbarang);
                        new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);
                    }
                    adapterKeranjang.notifyItemRemoved(pos);
                    list.remove(pos);
                } else {
                    Toast.makeText(CartActivity.this, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
                checkKeranjang();
            }

            @Override
            public void onFailure(Call<ResponsePos> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onMinusClick(int pos) {
        int jumlah = Integer.parseInt(list.get(pos).getKeranjangJumlah());
        if (jumlah > 1) {
            jumlah -= 1;
            final int aa = jumlah;
            int jumlahbarang = Integer.parseInt(list.get(pos).getHargaKategori()) - Integer.parseInt(list.get(pos).getDiskon());
            ServiceKeranjang service = Apiretro.getService().create(ServiceKeranjang.class);
            Call<ResponsePosJumlah> update = service.setJumlahKeranjang(list.get(pos).getIdKeranjang(), String.valueOf(jumlah));
            update.enqueue(new Callback<ResponsePosJumlah>() {
                @Override
                public void onResponse(Call<ResponsePosJumlah> call, Response<ResponsePosJumlah> response) {
                    if (response.body().getKode() == 1) {
                        list.get(pos).setKeranjangJumlah(String.valueOf(aa));
                        adapterKeranjang.notifyDataSetChanged();
                        if (list.get(pos).isSelectedCheck() == true) {
                            hasil = hasil - jumlahbarang;
                            new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);
                        }
                    } else if(response.body().getKode()==2){
                        String maxjumlah = response.body().getJumlah();
                        list.get(pos).setKeranjangJumlah(maxjumlah);
                        adapterKeranjang.notifyDataSetChanged();
                        Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponsePosJumlah> call, Throwable t) {
                    Toast.makeText(CartActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onPlusClick(int pos) {
        int jumlah = Integer.parseInt(list.get(pos).getKeranjangJumlah());
        jumlah += 1;
        final int aa = jumlah;
        int jumlahbarang = Integer.parseInt(list.get(pos).getHargaKategori()) - Integer.parseInt(list.get(pos).getDiskon());
        ServiceKeranjang service = Apiretro.getService().create(ServiceKeranjang.class);
        Call<ResponsePosJumlah> update = service.setJumlahKeranjang(list.get(pos).getIdKeranjang(), String.valueOf(jumlah));
        update.enqueue(new Callback<ResponsePosJumlah>() {
            @Override
            public void onResponse(Call<ResponsePosJumlah> call, Response<ResponsePosJumlah> response) {
                if (response.body().getKode() == 1) {
                    list.get(pos).setKeranjangJumlah(String.valueOf(aa));
                    adapterKeranjang.notifyDataSetChanged();
                    if (list.get(pos).isSelectedCheck() == true) {
                        hasil = hasil + jumlahbarang;
                        new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);
                    }
                } else if(response.body().getKode()==2){
                    int maxjumlah = Integer.parseInt(response.body().getJumlah());
                    list.get(pos).setKeranjangJumlah(String.valueOf(maxjumlah));
                    adapterKeranjang.notifyDataSetChanged();
                    Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(CartActivity.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePosJumlah> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onCheckBox(int position) {
        int jumlah = Integer.parseInt(list.get(position).getKeranjangJumlah());
        int harga = Integer.parseInt(list.get(position).getHargaKategori());
        int hargadiskon = Integer.parseInt(list.get(position).getDiskon());
        if (list.get(position).isSelectedCheck() == true) {
            list.get(position).setSelectedCheck(false);
            adapterKeranjang.notifyDataSetChanged();
            cobaList.remove(list.get(position));
            hasil = hasil - jumlah * (harga - hargadiskon);
            new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);
        } else {
            list.get(position).setSelectedCheck(true);
            adapterKeranjang.notifyDataSetChanged();
            cobaList.add(list.get(position));
            hasil = hasil + jumlah * (harga - hargadiskon);
            new CurrencyModel(String.valueOf(hasil), bind.cvcartTotalharga);
        }
    }
}