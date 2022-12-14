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
import com.example.acompstore.pModel.ModelCheckout;
import com.example.acompstore.pModel.ModelKeranjang;

import java.util.List;

public class AdapterCheckout extends RecyclerView.Adapter<AdapterCheckout.HolderData> {

    private Context context;
    private List<ModelKeranjang> list;

    public AdapterCheckout(Context context, List<ModelKeranjang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterCheckout.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_checkout, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCheckout.HolderData holder, int position) {
        ModelKeranjang data = list.get(position);
        holder.barang.setText(data.getNamaBarang());
        holder.kategori.setText(data.getNamaKategori());
        holder.jumlah.setText(data.getKeranjangJumlah());
        int jml = Integer.parseInt(data.getKeranjangJumlah());
        int hargakategori = Integer.parseInt(data.getHargaKategori()) - Integer.parseInt(data.getDiskon());
        new CurrencyModel(String.valueOf(jml * hargakategori), holder.subtotal);
        new CurrencyModel(String.valueOf(hargakategori), holder.harga);
        holder.hargadiskon.setPaintFlags(holder.hargadiskon.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        ImageConvertModel icm = new ImageConvertModel(context, data.getGambar(), holder.gambar);
        icm.ubahGambar();
        if (data.getDiskon().equals("0")){
            holder.hargadiskon.setText("");
        }else{
            new CurrencyModel(data.getHargaKategori(), holder.hargadiskon);
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
