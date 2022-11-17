package com.example.acompstore.pAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pModel.ModelKategori;

import java.util.List;

public class AdapterDetailKategori extends RecyclerView.Adapter<AdapterDetailKategori.HolderData> {

    private Context context;
    private List<ModelKategori> list;
    private final AdapterItemClick itemClick;
    private int selectedPosition = 0;


    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int index) {
        this.selectedPosition = index;
        notifyDataSetChanged();
    }

    public AdapterDetailKategori(Context context, List<ModelKategori> list, AdapterItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public AdapterDetailKategori.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_detailbarang, parent, false);
        AdapterDetailKategori.HolderData holder = new AdapterDetailKategori.HolderData(layout, itemClick);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterDetailKategori.HolderData holder, int position) {
        ModelKategori data = list.get(position);
        holder.idbarang.setText(data.getKategori_idBarang());
        holder.idkategori.setText(data.getIdKategori());
        holder.harga.setText(data.getHargaKategori());
        holder.diskon.setText(data.getDiskon());
        holder.gambar.setText(data.getGambar());
        holder.stok.setText(data.getStok());
        holder.btnamakategori.setText(data.getNamaKategori());

        if (selectedPosition != position) {
            holder.btnamakategori.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.btnamakategori.setTextColor(Color.parseColor("#838383"));
        } else {
            holder.btnamakategori.setBackgroundColor(Color.parseColor("#6D48AB"));
            holder.btnamakategori.setTextColor(Color.parseColor("#FFFFFF"));
        }
//        holder.btnamakategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//        row_index = holder.getAdapterPosition();
//        notifyDataSetChanged();
//        if (row_index == position) {
//            holder.btnamakategori.setBackgroundColor(Color.parseColor("#6D48AB"));
//            holder.btnamakategori.setTextColor(Color.parseColor("#FFFFFF"));
//        } else {
//            holder.btnamakategori.setBackgroundColor(Color.parseColor("#FFFFFF"));
//            holder.btnamakategori.setTextColor(Color.parseColor("#838383"));
//        }
//        holder.btnamakategori.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {
        private TextView idbarang, idkategori, harga, gambar, diskon, stok;
        private AppCompatButton btnamakategori;
        int row_index;

        public HolderData(@NonNull View itemView, AdapterItemClick adapterItemClick) {
            super(itemView);
            idbarang = itemView.findViewById(R.id.detailbarang_idbarang);
            idkategori = itemView.findViewById(R.id.detailbarang_idkategori);
            harga = itemView.findViewById(R.id.detailbarang_harga);
            gambar = itemView.findViewById(R.id.detailbarang_gambar);
            diskon = itemView.findViewById(R.id.detailbarang_diskon);
            stok = itemView.findViewById(R.id.detailbarang_stok);
            btnamakategori = itemView.findViewById(R.id.detailbarang_nama);


            btnamakategori.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterItemClick != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            adapterItemClick.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
