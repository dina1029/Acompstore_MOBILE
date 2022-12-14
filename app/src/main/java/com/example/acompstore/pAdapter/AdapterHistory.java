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
import com.example.acompstore.pModel.ModelHistory;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.HolderData> {

    private Context context;
    private List<ModelHistory> list;
    private final AdapterItemClick itemClick;

    public AdapterHistory(Context context, List<ModelHistory> list, AdapterItemClick itemClick) {
        this.context = context;
        this.list = list;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_history, parent, false);
        HolderData holder = new HolderData(layout, itemClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelHistory data = list.get(position);
        holder.kode.setText(data.getIdBeli());
        holder.tanggal.setText(data.getTanggal());
        holder.jumlah.setText(data.getJumlah());
        ImageConvertModel img = new ImageConvertModel(context, data.getGambar(), holder.gambar);
        img.ubahGambar();
        new CurrencyModel(data.getTotalBeli(), holder.total);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {

        private TextView kode, total, tanggal, jumlah;
        private ImageView gambar;

        public HolderData(@NonNull View itemView, AdapterItemClick adapter) {
            super(itemView);
            kode = itemView.findViewById(R.id.cvhist_kode);
            total = itemView.findViewById(R.id.cvhist_total);
            tanggal = itemView.findViewById(R.id.cvhist_tanggal);
            jumlah = itemView.findViewById(R.id.cvhist_jumlah);
            gambar = itemView.findViewById(R.id.cvhist_image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapter != null) {
                        if (getAdapterPosition()!=RecyclerView.NO_POSITION){
                            adapter.onItemClick(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }
}
