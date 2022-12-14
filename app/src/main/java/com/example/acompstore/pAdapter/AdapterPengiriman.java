package com.example.acompstore.pAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.CurrencyModel;
import com.example.acompstore.pPengiriman.pCost.ModelCosts;

import java.util.ArrayList;
import java.util.List;

public class AdapterPengiriman extends RecyclerView.Adapter<AdapterPengiriman.HolderData> {

    private Context context;
    private List<ModelCosts> list;
    private List<String> namaJasa;
    private final AdapterItemClick itemClick;

    public AdapterPengiriman(Context context, List<ModelCosts> list, List<String> namaJasa, AdapterItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.namaJasa = namaJasa;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_pengiriman, parent,false);
        HolderData holder = new HolderData(view, itemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelCosts data = list.get(position);
        if (namaJasa.get(position).equals("jne")){
            holder.nama.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logojne));
        }else if(namaJasa.get(position).equals("pos")){
            holder.nama.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logopos));
        }else if(namaJasa.get(position).equals("tiki")){
            holder.nama.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logo_tiki));
        }
        holder.jenis.setText("" + data.getDescription() + "(" + data.getService() + ")");
        holder.estimasi.setText("" + data.getCost().get(0).getEtd().toString().replace("HARI", ""));
        new CurrencyModel(data.getCost().get(0).getValue(), holder.harga);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {
        TextView jenis, harga, estimasi;
        ImageView nama;
        public HolderData(@NonNull View itemView, AdapterItemClick adapterItemClick) {
            super(itemView);
            nama = itemView.findViewById(R.id.cvpengiriman_nama);
            jenis = itemView.findViewById(R.id.cvpengiriman_jenislayanan);
            harga = itemView.findViewById(R.id.cvpengiriman_harga);
            estimasi = itemView.findViewById(R.id.cvpengiriman_estimasi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(adapterItemClick != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            adapterItemClick.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
