package com.example.acompstore.pActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityCheckoutPesananBinding;
import com.example.acompstore.pAdapter.AdapterCheckout;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.CartSaveProduct;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pFragment.HistoryFragment;
import com.example.acompstore.pModel.ModelKeranjang;
import com.example.acompstore.pPengiriman.PengirimanDialog;
import com.example.acompstore.pResponse.ResponsePosJumlah;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pService.ServiceCheckout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutPesananActivity extends AppCompatActivity implements AdapterItemClick {

    private ActivityCheckoutPesananBinding bind;
    private List<ModelKeranjang> list;
    private AdapterCheckout adapter;
    private PengirimanDialog ald;
    private int hargaAkhir = 0;
    private AlertDialog alert;
    private AppCompatButton bt_yes, bt_no, bt_atur;
    int mjumlah, mdiskon;
    int totaldanongkir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_checkout_pesanan);

        bt_atur = findViewById(R.id.btn_aturan);
        bt_atur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckoutPesananActivity.this, RulesActivity.class));
            }
        });

//        bind.cvtop.setVisibility(View.GONE);

        tampilBarang();
        jumlahBarang();
        bind.checkoutBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bind.cvtop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int weight = 0;
                for (int i = 0; i < list.size(); i++) {
                    weight = weight + (Integer.parseInt(list.get(i).getBerat()) * Integer.parseInt(list.get(i).getKeranjangJumlah()));
                }
                ald = new PengirimanDialog(CheckoutPesananActivity.this, String.valueOf(weight));
            }
        });
        bind.checkoutBtpesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bind.checkoutEstimasi.getText().toString().equals("") || bind.checkoutEstimasi.toString().isEmpty()){
                    Toast.makeText(CheckoutPesananActivity.this, "Pilh Jasa Pengiriman", Toast.LENGTH_SHORT).show();
                }else{
                    alertDialog();
                }
            }
        });
    }

    private void tampilBarang() {
        list = CartSaveProduct.readListKeranjang(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(CheckoutPesananActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView rc = bind.checkoutRcbarang;
        rc.setLayoutManager(layoutManager);
        rc.setLayoutManager(layoutManager);
        adapter = new AdapterCheckout(CheckoutPesananActivity.this, list);
        rc.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void jumlahBarang() {
        mjumlah = 0;
        int mharga = 0;
        mdiskon = 0;
        for (int i = 0; i < list.size(); i++) {
            mjumlah = mjumlah + Integer.parseInt(list.get(i).getKeranjangJumlah());
            mharga = mharga + Integer.parseInt(list.get(i).getKeranjangJumlah()) * (Integer.parseInt(list.get(i).getHargaKategori()) - Integer.parseInt(list.get(i).getDiskon()));
            mdiskon = mdiskon + Integer.parseInt(list.get(i).getDiskon());
        }
        hargaAkhir = mharga;
        bind.checkoutJumlah.setText(String.valueOf(mjumlah));
        new CurrencyModel(String.valueOf(hargaAkhir), bind.checkoutTotal);
        new CurrencyModel(String.valueOf(mdiskon), bind.checkoutDiskon);
    }

    private void alertDialog(){
        List<String> ljumlah = new ArrayList<>();
        List<String> detailHarga = new ArrayList<>();
        List<String> detailDiskon = new ArrayList<>();
        List<String> detbel_idKategori = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ljumlah.add(list.get(i).getKeranjangJumlah());
            int asharga = Integer.parseInt(list.get(i).getHargaKategori()) - Integer.parseInt(list.get(i).getDiskon());
            detailHarga.add(String.valueOf(asharga));
            detailDiskon.add(list.get(i).getDiskon());
            detbel_idKategori.add(list.get(i).getIdKategori());
        }
        String[] arrjumlah = ljumlah.toArray(new String[0]);
        String[] arrharga = detailHarga.toArray(new String[0]);
        String[] arrdiskon = detailDiskon.toArray(new String[0]);
        String[] arrkategori = detbel_idKategori.toArray(new String[0]);
        String idPembeliKu = SaveAccount.readDataPembeli(CheckoutPesananActivity.this).getIdPembeli();
        String jasaKurir = bind.checkoutNama.getText().toString();
        String serviceKurir = bind.checkoutJenislayanan.getText().toString();
        String biayaOngkir1 = bind.checkoutHarga.getText().toString().replace("Rp ", "");
        biayaOngkir1 = biayaOngkir1.replace(".", "");
        String biayaOngkir = biayaOngkir1.replace(",", "");
        String estimasiWaktu = bind.checkoutEstimasi.getText().toString();
        totaldanongkir = hargaAkhir + Integer.parseInt(biayaOngkir);
//        Toast.makeText(this, "ongkir " + biayaOngkir + " harga akhir " + hargaAkhir, Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutPesananActivity.this);
        View view1 = getLayoutInflater().inflate(R.layout.konfirmasi_transaksi_dialog, null);
        builder.setView(view1);
        alert = builder.create();
        alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alert.show();
        bt_yes = view1.findViewById(R.id.kontras_btyes);
        bt_no = view1.findViewById(R.id.kontras_btno);
        bt_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceCheckout service = Apiretro.getService().create(ServiceCheckout.class);
                Call<ResponsePosJumlah> transaction = service.setTransaction(String.valueOf(totaldanongkir), "Menunggu Disetujui"
                        , idPembeliKu, arrjumlah, arrharga, arrdiskon, arrkategori, jasaKurir, serviceKurir, biayaOngkir, estimasiWaktu);
                transaction.enqueue(new Callback<ResponsePosJumlah>() {
                    @Override
                    public void onResponse(Call<ResponsePosJumlah> call, Response<ResponsePosJumlah> response) {
                        response.body().getKode();
                        if (response.body().getKode() == 1) {
                            View view = getLayoutInflater().inflate(R.layout.toast_transaksiberhasil, null);
                            view.findViewById(R.id.toast_transaksiberhasil1);
                            Toast toast = new Toast(getApplicationContext());
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.setView(view);
                            toast.show();
                            toast.setGravity(Gravity.TOP| Gravity.LEFT, 180, 10);
                            Intent i = new Intent(CheckoutPesananActivity.this, SplashScreenTransaksi.class);
                            startActivity(i);
                            ActivityCompat.finishAffinity(CheckoutPesananActivity.this);
                        } else if (response.body().getKode()==2){
                            int urutan = Integer.parseInt(response.body().getJumlah());
                            Toast.makeText(CheckoutPesananActivity.this, "Jumlah " + list.get(urutan).getNamaBarang() + response.body().getPesan(), Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(CheckoutPesananActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePosJumlah> call, Throwable t) {
                        View view = getLayoutInflater().inflate(R.layout.toast_transaksiberhasil, null);
                        view.findViewById(R.id.toast_transaksiberhasil1);
                        Toast toast = new Toast(getApplicationContext());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(view);
                        toast.show();
                        toast.setGravity(Gravity.TOP| Gravity.LEFT, 250, 10);
                        Intent intent = new Intent(CheckoutPesananActivity.this, HistoryFragment.class);
                        startActivity(intent);
                    }
                });
            }
        });
        bt_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.dismiss();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        bind.checkoutNama.setText(ald.getNamaJasa().get(position));
        bind.checkoutJenislayanan.setText(ald.getList().get(position).getDescription());
        new CurrencyModel(ald.getList().get(position).getCost().get(0).getValue(), bind.checkoutHarga);
        bind.checkoutEstimasi.setText(ald.getList().get(position).getCost().get(0).getEtd().toString().replace("HARI", "")
                + "Hari");
        ald.getAlert().dismiss();
        int myharga = hargaAkhir + Integer.parseInt(ald.getList().get(position).getCost().get(0).getValue());
        new CurrencyModel(String.valueOf(myharga), bind.checkoutTotal);
    }
}