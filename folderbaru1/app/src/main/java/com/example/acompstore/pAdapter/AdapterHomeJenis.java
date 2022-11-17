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

    private int row_index = 0;
    private Context context;
    private List<ModelJenisBarang> list;

    public AdapterHomeJenis(Context context, List<ModelJenisBarang> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_homejenis, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        ModelJenisBarang data = list.get(position);
        holder.idJenis.setText(data.getIdJenis());
        holder.btjenis.setText(data.getNamaJenis());
        holder.btjenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });
        if (row_index == position){
            holder.btjenis.setBackgroundColor(Color.parseColor("#FFD600"));
        }else{
            holder.btjenis.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView idJenis;
        AppCompatButton btjenis;
        public HolderData(@NonNull View itemView) {
            super(itemView);
            idJenis = itemView.findViewById(R.id.homejenis_idjenis);
            btjenis = itemView.findViewById(R.id.homejenis_btjenis);
        }
    }
}
