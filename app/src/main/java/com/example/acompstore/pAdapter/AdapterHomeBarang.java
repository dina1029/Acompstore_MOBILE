package com.example.acompstore.pAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.google.android.material.transition.Hold;

import java.util.List;

public class AdapterHomeBarang extends RecyclerView.Adapter<AdapterHomeBarang.HolderData> {

    private Context context;
    private List<ModelHomeBarang> list;
    private final AdapterItemClick itemClick;

    public AdapterHomeBarang(Context context, List<ModelHomeBarang> list, AdapterItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_homebarang, parent, false);
        HolderData holder = new HolderData(layout, itemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelHomeBarang mh = list.get(position);
        holder.idBarang.setText(mh.getIdbarang());
        holder.idKategori.setText(mh.getIdKategori());
        holder.nama.setText(mh.getNamaBarang());
        ImageConvertModel icm = new ImageConvertModel(context, mh.getGambar(), holder.gambar);
        icm.ubahGambar();
        CurrencyModel cm = new CurrencyModel(mh.getHargaKategori(), holder.harga);
        if (mh.getTerjual()==null){
            holder.terjual.setText("0 Terjual");
        }else{
            holder.terjual.setText(mh.getTerjual() + " Terjual");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {
        TextView idBarang, idKategori, harga, nama, terjual;
        ImageView gambar;
        public HolderData(@NonNull View itemView, AdapterItemClick adapterItemClick) {
            super(itemView);
            idBarang = itemView.findViewById(R.id.homebarang_idBarang);
            idKategori = itemView.findViewById(R.id.homebarang_idCategory);
            harga = itemView.findViewById(R.id.homebarang_harga);
            nama = itemView.findViewById(R.id.homebarang_nama);
            gambar = itemView.findViewById(R.id.homebarang_gambar);
            terjual = itemView.findViewById(R.id.homebarang_terjual);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adapterItemClick != null){
                        int pos = getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            adapterItemClick.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
