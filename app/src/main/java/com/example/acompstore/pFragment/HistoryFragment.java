package com.example.acompstore.pFragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.FragmentHistoryBinding;
import com.example.acompstore.pActivity.DetailTransaksiActivity;
import com.example.acompstore.pAdapter.AdapterHistory;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelHistory;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponseHistory;
import com.example.acompstore.pService.ServiceHistory;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment implements AdapterItemClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HistoryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentHistoryBinding bind;
    private AdapterHistory adapter;
    private List<ModelHistory> list;
    private byte kodeButton = 1;
    private SharedPreferences shared;

    @Override
    public void onResume() {
        super.onResume();
        shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        boolean checkKode = shared.getBoolean("homeId", false);
        if (checkKode==true) {
            FragmentManager fm = getFragmentManager();
            if (fm != null) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.home_frmlayout, new HistoryFragment());
                ft.commit();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("homeId", false);
        editor.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false);
        View view1 = bind.getRoot();

        shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("homeId", false);
        editor.commit();

        bind.histRchistory.setVisibility(View.GONE);
        bind.histNullview.setVisibility(View.GONE);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        bind.histRchistory.setLayoutManager(layoutManager);

        bind.histBtpending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanHistory("Menunggu Disetujui");
            }
        });

        bind.histDisetujui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanHistory("Disetujui");
            }
        });
        bind.histDikirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanHistory("Dikirim");
            }
        });
        bind.histBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanHistory("Dibatalkan");
            }
        });
        bind.histSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanHistory("Selesai");
            }
        });

        bind.histBtpending.performClick();

        return view1;
    }

    private void tampilkanProsesHistory(){
        String idPembeli = SaveAccount.readDataPembeli(getContext()).getIdPembeli();
        ServiceHistory service = Apiretro.getService().create(ServiceHistory.class);
        Call<ResponseHistory> getProsesHistory = service.getProsesHistory(idPembeli);
        getProsesHistory.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.body().getKode() == 200) {
                    list = response.body().getData();
                    adapter = new AdapterHistory(getContext(), list, HistoryFragment.this);
                    bind.histRchistory.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "Transaksi Kosong", Toast.LENGTH_SHORT).show();
                    list = new ArrayList<>();
                    adapter = new AdapterHistory(getContext(), list, HistoryFragment.this);
                    bind.histRchistory.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void tampilkanHistory(String status){
        String idPembeli = SaveAccount.readDataPembeli(getContext()).getIdPembeli();
        ServiceHistory service = Apiretro.getService().create(ServiceHistory.class);
        Call<ResponseHistory> getHistory = service.getHistory(idPembeli, status);
        getHistory.enqueue(new Callback<ResponseHistory>() {
            @Override
            public void onResponse(Call<ResponseHistory> call, Response<ResponseHistory> response) {
                if (response.body().getKode() == 200) {
                    list = response.body().getData();
                    adapter = new AdapterHistory(getContext(), list, HistoryFragment.this);
                    bind.histRchistory.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    bind.histRchistory.setVisibility(View.VISIBLE);
                    bind.histNullview.setVisibility(View.GONE);
                }else{
                    list = new ArrayList<>();
                    adapter = new AdapterHistory(getContext(), list, HistoryFragment.this);
                    bind.histRchistory.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    bind.histRchistory.setVisibility(View.GONE);
                    bind.histNullview.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseHistory> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), DetailTransaksiActivity.class);
        intent.putExtra("idBeli", list.get(position).getIdBeli());
        startActivity(intent);
    }
}