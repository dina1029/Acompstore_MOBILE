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
import com.example.acompstore.pModel.ModelCheckout;
import com.example.acompstore.pModel.ModelKeranjang;

import java.util.List;

public class AdapterCheckout extends RecyclerView.Adapter<AdapterCheckout.HolderData> {

    private Context context;
    private List<ModelCheckout> list;

    public AdapterCheckout(Context context, List<ModelCheckout> list) {
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
        ModelCheckout data = list.get(position);
        holder.nama.setText(data.getNamaBarang());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView nama, subtotal, harga, hargadiskon, jumlah;
        ImageView gambar;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.cvcheckout_nama);
            harga = itemView.findViewById(R.id.cvcheckout_harga);
            hargadiskon = itemView.findViewById(R.id.cvcheckout_hargadiskon);
            jumlah = itemView.findViewById(R.id.cvcheckout_jumlah);
            subtotal = itemView.findViewById(R.id.cvcheckout_subtotal);
            gambar = itemView.findViewById(R.id.cvcheckout_gambar);
        }
    }
}
