package com.example.acompstore.pAdapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pModel.ModelKeranjang;
import com.example.acompstore.pModel.ModelTransDetail;

import java.util.List;

public class AdapterDetailBeli extends RecyclerView.Adapter<AdapterDetailBeli.HolderData> {

    private Context context;
    private List<ModelTransDetail> list;

    public AdapterDetailBeli(Context context, List<ModelTransDetail> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDetailBeli.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_checkout, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDetailBeli.HolderData holder, int position) {
        ModelTransDetail data = list.get(position);
        holder.barang.setText(data.getNamaBarang());
        holder.kategori.setText(data.getNamaKategori());
        holder.jumlah.setText(data.getJumlah());
        int jml = Integer.parseInt(data.getJumlah());
        int hargakategori = Integer.parseInt(data.getDetailHarga()) - Integer.parseInt(data.getDetailDiskon());
        new CurrencyModel(String.valueOf(jml * hargakategori), holder.subtotal);
        new CurrencyModel(String.valueOf(hargakategori), holder.harga);
        holder.hargadiskon.setPaintFlags(holder.hargadiskon.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        ImageConvertModel icm = new ImageConvertModel(context, data.getGambar(), holder.gambar);
        icm.ubahGambar();
        if (data.getDetailDiskon().equals("0")){
            holder.hargadiskon.setText("");
        }else{
            new CurrencyModel(data.getDetailHarga(), holder.hargadiskon);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView barang, subtotal, harga, hargadiskon, jumlah, kategori;
        ImageView gambar;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            barang = itemView.findViewById(R.id.cvcheckout_nama);
            kategori = itemView.findViewById(R.id.cvcheckout_kategori);
            harga = itemView.findViewById(R.id.cvcheckout_harga);
            hargadiskon = itemView.findViewById(R.id.cvcheckout_hargadiskon);
            jumlah = itemView.findViewById(R.id.cvcheckout_jumlah);
            subtotal = itemView.findViewById(R.id.cvcheckout_subtotal);
            gambar = itemView.findViewById(R.id.cvcheckout_gambar);
        }
    }
}
