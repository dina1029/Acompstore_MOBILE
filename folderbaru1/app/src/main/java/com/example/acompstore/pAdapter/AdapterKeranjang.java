package com.example.acompstore.pAdapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pModel.ModelKeranjang;

import java.util.List;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.HolderData> {

    private Context context;
    private List<ModelKeranjang> list;
    private final AdapterCartClick itemClick;

    public AdapterKeranjang(Context context, List<ModelKeranjang> list, AdapterCartClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_cart, parent, false);
        HolderData holder = new HolderData(view, itemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelKeranjang data = list.get(position);
        holder.namaKategori.setText(list.get(position).getNamaKategori());
        holder.namaBarang.setText(list.get(position).getNamaBarang());
        holder.diskon.setText(list.get(position).getDiskon());
        holder.jumlah.setText(list.get(position).getKeranjangJumlah());
        ImageConvertModel icm = new ImageConvertModel(context, list.get(position).getGambar(), holder.gambarBarang);
        icm.ubahGambar();
        int mydiskon = Integer.parseInt(list.get(position).getDiskon());

        if (mydiskon > 0){
            int myharga = Integer.parseInt(list.get(position).getHargaKategori());
            int hasildiskon = mydiskon * 100 / myharga;
            int hasilpengurangan = myharga - mydiskon;
            holder.diskon.setText("Disount " + hasildiskon + "%");
            new CurrencyModel(String.valueOf(myharga), holder.hargadiskon);
            new CurrencyModel(String.valueOf(hasilpengurangan), holder.harga);
            holder.hargadiskon.setPaintFlags(holder.hargadiskon.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }else{
            holder.diskon.setText("");
            holder.hargadiskon.setText("");
            new CurrencyModel(list.get(position).getHargaKategori(), holder.harga);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder{
        private TextView namaBarang, namaKategori, diskon, harga, hargadiskon, jumlah, minus, plus;
        private ImageButton bt_delete;
        private ImageView gambarBarang;
        private CheckBox checkBox;

        public HolderData(@NonNull View itemView, AdapterCartClick adapterCartClick) {
            super(itemView);
            namaBarang = itemView.findViewById(R.id.cvcart_nama);
            namaKategori = itemView.findViewById(R.id.cvcart_kategori);
            diskon = itemView.findViewById(R.id.cvcart_discount);
            harga = itemView.findViewById(R.id.cvcart_harga);
            hargadiskon = itemView.findViewById(R.id.cvcart_hargadiskon);
            jumlah = itemView.findViewById(R.id.cvcart_jumlahbarang);
            minus = itemView.findViewById(R.id.cvcart_minus);
            plus = itemView.findViewById(R.id.cvcart_plus);
            bt_delete = itemView.findViewById(R.id.cvcart_btdelete);
            gambarBarang = itemView.findViewById(R.id.cvcart_gambar);
            checkBox = itemView.findViewById(R.id.cvcart_check);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterCartClick != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            adapterCartClick.onCheckBox(pos);
                        }
                    }
                }
            });

            bt_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterCartClick != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            adapterCartClick.onDeleteClick(pos);;
                        }
                    }
                }
            });
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterCartClick!=null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            adapterCartClick.onMinusClick(pos);
                        }
                    }
                }
            });
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterCartClick != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            adapterCartClick.onPlusClick(pos);
                        }
                    }
                }
            });
        }
    }
    public interface AdapterCartClick{
        public void onDeleteClick(int position);
        public void onMinusClick(int position);
        public void onPlusClick(int position);
        public void onCheckBox(int position);
    }
}


