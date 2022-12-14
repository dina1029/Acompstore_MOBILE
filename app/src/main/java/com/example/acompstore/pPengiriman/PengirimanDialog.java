package com.example.acompstore.pPengiriman;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdapter.AdapterPengiriman;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pPengiriman.pCost.ModelCosts;
import com.example.acompstore.pPengiriman.pCost.ResponseCost;
import com.example.acompstore.pResponse.ResponseAlamatToko;
import com.example.acompstore.pService.ServiceAlamatToko;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengirimanDialog extends AlertDialog{


    private List<ModelCosts> list = new ArrayList<>();
    private Context context;
    RecyclerView recyclerView;
    private AlertDialog alert;
    private AdapterPengiriman adapter;
    private String kotaToko;
    private String kotaPembeli;

    public List<String> getNamaJasa() {
        return namaJasa;
    }

    public AlertDialog getAlert() {
        return alert;
    }

    public List<ModelCosts> getList() {
        return list;
    }

    private String weight;
    private List<String> namaJasa = new ArrayList<>();

    public PengirimanDialog(Context context, String weight){
        super(context);
        this.context = context;
        this.weight = weight;
        Builder builder = new Builder(context);
        View view = getLayoutInflater().inflate(R.layout.pengiriman_dialog, null);
        builder.setView(view);
        alert = builder.create();
        alert.show();
        recyclerView = view.findViewById(R.id.pedal_rcpengiriman);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        ServiceAlamatToko service = Apiretro.getService().create(ServiceAlamatToko.class);
        Call<ResponseAlamatToko> toko = service.getAlamatToko();
        toko.enqueue(new Callback<ResponseAlamatToko>() {
            @Override
            public void onResponse(Call<ResponseAlamatToko> call, Response<ResponseAlamatToko> response) {
                kotaToko = response.body().getData().get(0).getId_kota();
                getJasaKirim("jne", kotaToko);
                getJasaKirim("pos", kotaToko);
                getJasaKirim("tiki", kotaToko);
            }

            @Override
            public void onFailure(Call<ResponseAlamatToko> call, Throwable t) {
                Toast.makeText(context, "Alamat Toko Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getJasaKirim(String jasakirim, String toko){
        kotaPembeli = SaveAccount.readDataPembeli(context).getKota();
//        Toast.makeText(context, "pem:" + kotaPembeli + ", toko:" + toko + ", weight:" + weight + "jasa:" + jasakirim, Toast.LENGTH_LONG).show();
        ServicePengiriman service = Apiretro.getRajaOngkirUrl().create(ServicePengiriman.class);
        Call<ResponseCost> costData = service.getCost(kotaPembeli, toko, weight, jasakirim);
        costData.enqueue(new Callback<ResponseCost>() {
            @Override
            public void onResponse(Call<ResponseCost> call, Response<ResponseCost> response) {
                list.addAll(response.body().getRajaongkir().getResults().get(0).getCosts());
                List<ModelCosts> modelBaru = response.body().getRajaongkir().getResults().get(0).getCosts();
                for (int i=0; i<modelBaru.size(); i++){
                    namaJasa.add(response.body().getRajaongkir().getResults().get(0).getCode());
                }
                adapter = new AdapterPengiriman(context, list, namaJasa, (AdapterItemClick) context);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseCost> call, Throwable t) {
                Toast.makeText(context, "Jasa Kirim Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
