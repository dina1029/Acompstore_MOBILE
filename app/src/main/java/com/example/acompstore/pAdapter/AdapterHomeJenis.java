package com.example.acompstore.pAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acompstore.R;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pModel.ModelJenisBarang;

import java.util.List;

public class AdapterHomeJenis extends RecyclerView.Adapter<AdapterHomeJenis.HolderData> {

    private Context context;
    private List<ModelJenisBarang> list;
    private final ItemJenisClick itemJenisClick;
    private int selectedPosition = 0;

    public AdapterHomeJenis(Context context, List<ModelJenisBarang> list, ItemJenisClick itemJenisClick) {
        this.context = context;
        this.list = list;
        this.itemJenisClick = itemJenisClick;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_homejenis, parent, false);
        HolderData holder = new HolderData(layout, itemJenisClick);
        return holder;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelJenisBarang data = list.get(position);
        holder.idJenis.setText(data.getIdJenis());
        holder.btjenis.setText(data.getNamaJenis());
        if (selectedPosition != position) {
            holder.btjenis.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.btjenis.setTextColor(Color.parseColor("#000000"));
        } else {
            holder.btjenis.setBackgroundColor(Color.parseColor("#FFD600"));
            holder.btjenis.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderData extends RecyclerView.ViewHolder {
        TextView idJenis;
        AppCompatButton btjenis;
        public HolderData(@NonNull View itemView, ItemJenisClick adapterItemClick) {
            super(itemView);
            idJenis = itemView.findViewById(R.id.homejenis_idjenis);
            btjenis = itemView.findViewById(R.id.homejenis_btjenis);

            btjenis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (adapterItemClick != null) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                            adapterItemClick.onItemJenisClickListener(getAdapterPosition());
                        }
                    }
                }
            });
        }
    }
    public interface ItemJenisClick{
        public void onItemJenisClickListener(int pos);
    }
}
