package com.example.acompstore.pActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityDetailTransaksiBinding;
import com.example.acompstore.pAdapter.AdapterDetailBeli;
import com.example.acompstore.pAdditional.BottomSheet_Detrans;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelBeli;
import com.example.acompstore.pModel.ModelTransDetail;
import com.example.acompstore.pResponse.ResponseGetDetailBeli;
import com.example.acompstore.pService.ServiceTransaksi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    private ActivityDetailTransaksiBinding bind;
    private List<ModelTransDetail> detailBeli;
    private ModelBeli beli;
    private AdapterDetailBeli adapter;
    private int mjumlah, mharga, mdiskon, hargaAkhir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_detail_transaksi);

        LinearLayoutManager layoutManager = new LinearLayoutManager(DetailTransaksiActivity.this,
                RecyclerView.VERTICAL, false);
        bind.detransRcbarang.setLayoutManager(layoutManager);
        getDetail();

        bind.detransBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        bind.detransBtbottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheet_Detrans dialog = new BottomSheet_Detrans(beli.getIdBeli());
                dialog.show(getSupportFragmentManager(), "batalkan_transaksi");
            }
        });

        bind.detransCopykodetrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipBoard(bind.detransKodetrans.getText().toString(), "Kode transaksi");
            }
        });

        bind.detransCopyrekening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipBoard(bind.detransNorekening.getText().toString(), "Nomor rekening");
            }
        });

        bind.detransCopyresi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clipBoard(bind.detransCopyresi.getText().toString(), "Nomor resi");
            }
        });
        bind.checkoutAturan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailTransaksiActivity.this, RulesActivity.class));
            }
        });

    }

    private void clipBoard(String text, String nama){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copy", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, nama + " telah disalin", Toast.LENGTH_SHORT).show();
    }

    private void checkStatus(){
        AppCompatButton btBottom = findViewById(R.id.detrans_btbottom);
        TextView textInformation, textResi, textRekening;
        textInformation = findViewById(R.id.detrans_info);
        LinearLayout viewPengiriman = findViewById(R.id.detrans_viewpengiriman);
        ConstraintLayout viewRekening = findViewById(R.id.detrans_viewrekening);
        ConstraintLayout viewResi = findViewById(R.id.detrans_viewresi);
        String cStatus = bind.detransStatus.getText().toString();
        if (cStatus.equals("Menunggu Disetujui")){
            textInformation.setText("Mohon tunggu hingga penjual melakukan konfirmasi pada barang yang akan anda beli.");
            btBottom.setVisibility(View.VISIBLE);
            textInformation.setVisibility(View.VISIBLE);
            viewRekening.setVisibility(View.GONE);
            viewResi.setVisibility(View.GONE);
        }else if (cStatus.equals("Disetujui")){
            textInformation.setText("Mohon transferkan uang ke nomor rekening " + beli.getRekening()
                    + " untuk proses transaksi. Kirimkan bukti pembayaran melalui chat admin agar barang segera dikirimkan.");
            btBottom.setVisibility(View.VISIBLE);
            textInformation.setVisibility(View.VISIBLE);
            viewRekening.setVisibility(View.VISIBLE);
            viewResi.setVisibility(View.GONE);
        }else if (cStatus.equals("Dikirim")){
            textInformation.setText("Barang telah dikirimkan dengan menggunakan " + beli.getJasaKurir()
                    + "(" + beli.getServiceKurir() + ") dengan nomor resi " + beli.getNoResi()
                    + ". Hubungi penjual jika terdapat kendala.");
            btBottom.setVisibility(View.GONE);
            textInformation.setVisibility(View.VISIBLE);
            viewRekening.setVisibility(View.VISIBLE);
            viewResi.setVisibility(View.VISIBLE);
        }else if (cStatus.equals("Dibatalkan")){
            textInformation.setText("Transaksi dengan kode " + beli.getIdBeli() + " telah dibatalkan oleh "
                    + beli.getBapes_Oleh() + " karena " + beli.getBapes_Alasan());
            btBottom.setVisibility(View.GONE);
            textInformation.setVisibility(View.VISIBLE);
            viewRekening.setVisibility(View.GONE);
            viewResi.setVisibility(View.GONE);
        }else if (cStatus.equals("Selesai")){
            textInformation.setText("Barang telah sampai di tujuan. Konfirmasi kepada penjual jika barang belum sampai atau terjadi kendala lain");
            btBottom.setVisibility(View.GONE);
            textInformation.setVisibility(View.VISIBLE);
            viewRekening.setVisibility(View.VISIBLE);
            viewResi.setVisibility(View.VISIBLE);
        }
    }

    private void getDetail() {
        Bundle extras = getIntent().getExtras();
        String kodeBeli = extras.getString("idBeli");
        ServiceTransaksi service = Apiretro.getService().create(ServiceTransaksi.class);
        Call<ResponseGetDetailBeli> beliCall = service.getDetailBeli(kodeBeli);
        beliCall.enqueue(new Callback<ResponseGetDetailBeli>() {
            @Override
            public void onResponse(Call<ResponseGetDetailBeli> call, Response<ResponseGetDetailBeli> response) {
                if (response.body().getKode() == 200) {
                    beli = response.body().getBeli();
                    detailBeli = response.body().getDetail_beli();
                    adapter = new AdapterDetailBeli(DetailTransaksiActivity.this, detailBeli);
                    bind.detransRcbarang.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    bind.detransKodetrans.setText(beli.getIdBeli());
                    bind.detransTanggal.setText(beli.getTanggal());
                    bind.detransStatus.setText(beli.getStatus());
                    bind.detransNorekening.setText(beli.getRekening());
                    bind.detransResi.setText(beli.getNoResi());
                    bind.detransTanggalkirim.setText(beli.getTanggalDikirim());
                    bind.detransNamakurir.setText(beli.getJasaKurir());
                    bind.detransLayanankurir.setText(beli.getServiceKurir());
                    bind.detransEstimasi.setText(beli.getEstimasiWaktu());
                    bind.detransBankname.setText(beli.getNamaBank());
                    new CurrencyModel(beli.getTotalBeli(), bind.detransTotal);
                    new CurrencyModel(beli.getBiayaOngkir(), bind.detransOngkir);
                    jumlahBarang();
                    checkStatus();
                } else {
                    Toast.makeText(DetailTransaksiActivity.this, "Data Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetDetailBeli> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void jumlahBarang() {
        mjumlah = 0;
        mharga = 0;
        mdiskon = 0;
        for (int i = 0; i < detailBeli.size(); i++) {
            mjumlah = mjumlah + Integer.parseInt(detailBeli.get(i).getJumlah());
            mharga = mharga + mjumlah * (Integer.parseInt(detailBeli.get(i).getDetailHarga()) - Integer.parseInt(detailBeli.get(i).getDetailDiskon()));
            mdiskon = mdiskon + Integer.parseInt(detailBeli.get(i).getDetailDiskon());
        }
        hargaAkhir = mharga;
        bind.detransJumlah.setText(String.valueOf(mjumlah));
//        new CurrencyModel(String.valueOf(mharga), bind.detransTotal);
        new CurrencyModel(String.valueOf(mdiskon), bind.detransDiskon);
    }
}