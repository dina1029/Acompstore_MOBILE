package com.example.acompstore.pAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pModel.ModelNotification;
import com.example.acompstore.pModel.ModelPembeliAlamat;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.HolderData> {
    private Context context;
    private List<ModelNotification> modelnotifList;

    public AdapterNotification(Context context, List<ModelNotification> modelnotifList) {
        this.context = context;
        this.modelnotifList = modelnotifList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_notification, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelNotification dm = modelnotifList.get(position);

        holder.txtkode.setText(dm.getKodeupdatebeli());
        holder.txtwaktu.setText(dm.getTanggalupdatebeli());
        holder.txttransaksi.setText(dm.getStatusupdatebeli());

    }

    @Override
    public int getItemCount() {
        return modelnotifList.size();

    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView txtkode, txttransaksi, txtwaktu;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            txtkode = itemView.findViewById(R.id.txt_kode);
            txttransaksi = itemView.findViewById(R.id.txt_transaksi);
            txtwaktu = itemView.findViewById(R.id.txt_waktu);


        }
    }
}