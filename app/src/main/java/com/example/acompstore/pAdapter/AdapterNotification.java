package com.example.acompstore.pAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.ImageConvertModel;
import com.example.acompstore.pModel.ModelKeranjang;
import com.example.acompstore.pModel.ModelNotification;

import java.util.List;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.HolderData> {
    private Context context;
    private List<ModelNotification> modelnotifList;
    private final AdapterItemClick itemClick;

    public AdapterNotification(Context context, List<ModelNotification> modelnotifList, AdapterItemClick itemClick) {
        this.context = context;
        this.modelnotifList = modelnotifList;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_notification, parent, false);
        HolderData holder = new HolderData(layout, itemClick);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        TextView header = holder.header;
        TextView rincian = holder.rincian;
        ModelNotification dm = modelnotifList.get(position);
        String status = dm.getStatusUpdateBeli();

        ImageConvertModel icm = new ImageConvertModel(context, modelnotifList.get(position).getGambar(), holder.imgnotif);
        icm.ubahGambar();
        holder.txtwaktu.setText(dm.getTanggalUpdateBeli());
        if (status.equals("Menunggu Disetujui")){
            header.setText("Menunggu persetujuan penjual");
            rincian.setText("Pesanan " + dm.getKodeUpdateBeli() + " sedang diproses. Tunggu konfirmasi " +
                    "penjual untuk melanjutkan transaksi. Hubungi penjual agar pesananmu segera diproses.");
        }else if(status.equals("Disetujui")){
            header.setText("Pesanan telah disetujui");
            rincian.setText("Pesanan " + dm.getKodeUpdateBeli() + " telah disetujui. Segera transfer " +
                    "total pembayaran pada nomor rekening yang telah diberika untuk melanjutkan transaksi.");
        }else if(status.equals("Dikirim")){
            header.setText("Pesanan dikirim");
            rincian.setText("Pesanan " + dm.getKodeUpdateBeli() + " telah dikirim oleh penjual. Anda bisa melacaknya dengan nomor resi yang telah diberikan.");
        }else if(status.equals("Dibatalkan")){
            header.setText("Pesanan dibatalkan");
            rincian.setText("Pesanan " + dm.getKodeUpdateBeli() + " dibatalkan. klik untuk melihat rincian pembatalan.");
        }else if(status.equals("Selesai")){
            header.setText("Pesanan telah sampai");
            rincian.setText("Pesanan " + dm.getKodeUpdateBeli() + " dikonfirmasi oleh penjual telah tiba. " +
                    "Hubungi penjual jika pesananmu belum tiba atau terdapat kendala lain.");
        }

        if (dm.getDibaca().toString().equals("t")){
            holder.cv_item.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

    }

    @Override
    public int getItemCount() {
        return modelnotifList.size();

    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView header, rincian, txtwaktu;
        ImageView imgnotif;
        CardView cv_item;

        public HolderData(@NonNull View itemView, AdapterItemClick adapter) {
            super(itemView);

            imgnotif = itemView.findViewById(R.id.img_notif);
            header = itemView.findViewById(R.id.cvnotif_header);
            rincian = itemView.findViewById(R.id.cvnotif_rincian);
            txtwaktu = itemView.findViewById(R.id.cvnotif_waktu);
            cv_item = itemView.findViewById(R.id.cardcv_item);
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