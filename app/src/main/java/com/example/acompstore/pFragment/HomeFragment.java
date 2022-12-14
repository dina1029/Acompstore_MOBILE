package com.example.acompstore.pFragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.FragmentHomeBinding;
import com.example.acompstore.pActivity.CartActivity;
import com.example.acompstore.pActivity.DetailBarangActivity;
import com.example.acompstore.pActivity.HomeSearchActivity;
import com.example.acompstore.pAdapter.AdapterHomeBarang;
import com.example.acompstore.pAdapter.AdapterHomeJenis;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pModel.ModelJenisBarang;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.example.acompstore.pResponse.ResponseGetBarang;
import com.example.acompstore.pResponse.ResponseGetJenis;
import com.example.acompstore.pService.ServiceHome;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AdapterItemClick, AdapterHomeJenis.ItemJenisClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private FragmentHomeBinding bind;
    private List<ModelHomeBarang> list = new ArrayList<>();
    private List<ModelJenisBarang> listJenis = new ArrayList<>();
    private AdapterHomeJenis adapterJenis;
    private AdapterHomeBarang adapterBarang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = bind.getRoot();
        retrieve_barang(view);
        retrieve_jenis(view);
        bind.homeNullview.setVisibility(View.GONE);

        bind.homeBtcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeSearchActivity.class));
            }
        });

        bind.homeBtmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wpurl = "https://wa.me/+6281333793691?";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });

        bind.homeBtcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CartActivity.class));
            }
        });

        return view;
    }
    private View retrieve_barang(View view){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        bind.homeRecycler.setLayoutManager(gridLayoutManager);
        bind.homeRecycler.setNestedScrollingEnabled(false);
        ServiceHome service = Apiretro.getService().create(ServiceHome.class);
        Call<ResponseGetBarang> tampildata = service.getHomeBarangNoDiskon(0);
        tampildata.enqueue(new Callback<ResponseGetBarang>() {
            @Override
            public void onResponse(Call<ResponseGetBarang> call, Response<ResponseGetBarang> response) {
                if(response.body().getKode()==1) {
                    list = response.body().getData();
                    adapterBarang = new AdapterHomeBarang(view.getContext(), list, HomeFragment.this);
                    bind.homeRecycler.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                }else{
                    Toast.makeText(getContext(), "Data Kosong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBarang> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private View retrieve_jenis(View view){
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext()
                , LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mlist = bind.homeJenisbarang;
        mlist.setLayoutManager(layoutManager);
        mlist.setHasFixedSize(true);
        ServiceHome service = Apiretro.getService().create(ServiceHome.class);
        Call<ResponseGetJenis> tampildata1 = service.getHomeJenisBarang();
        tampildata1.enqueue(new Callback<ResponseGetJenis>() {
            @Override
            public void onResponse(Call<ResponseGetJenis> call, Response<ResponseGetJenis> response) {
                byte kode = response.body().getKode();
                if (kode == 1){
                    listJenis = response.body().getData();
                    adapterJenis = new AdapterHomeJenis(view.getContext(), listJenis, HomeFragment.this);
                    bind.homeJenisbarang.setAdapter(adapterJenis);
                    adapterJenis.notifyDataSetChanged();
                }else{
                    Toast.makeText(getActivity(), "Data Kosong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetJenis> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(getActivity(), DetailBarangActivity.class);
        intent.putExtra("idBarang", list.get(position).getIdBarang());
        intent.putExtra("terjual", list.get(position).getTerjual());
        intent.putExtra("deskripsi", list.get(position).getDeskripsiBarang());
        intent.putExtra("gambar", list.get(position).getGambar());
        intent.putExtra("idKategori", list.get(position).getIdKategori());
        intent.putExtra("nama", list.get(position).getNamaBarang());
        intent.putExtra("harga", list.get(position).getHargaKategori());
        intent.putExtra("stok", list.get(position).getStok());
        intent.putExtra("diskon", list.get(position).getDiskon());
        startActivity(intent);
    }

    @Override
    public void onItemJenisClickListener(int pos) {
        adapterJenis.setSelectedPosition(pos);
        ServiceHome service = Apiretro.getService().create(ServiceHome.class);
        Call<ResponseGetBarang> tampildata = service.getHomeBarangNoDiskon(Integer.parseInt(listJenis.get(pos).getIdJenis()));
        tampildata.enqueue(new Callback<ResponseGetBarang>() {
            @Override
            public void onResponse(Call<ResponseGetBarang> call, Response<ResponseGetBarang> response) {
                if(response.body().getKode()==1) {
                    list = response.body().getData();
                    adapterBarang = new AdapterHomeBarang(getContext(), list, HomeFragment.this);
                    bind.homeRecycler.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                    bind.homeNullview.setVisibility(View.GONE);
                }else{
                    list = new ArrayList<>();
                    adapterBarang = new AdapterHomeBarang(getContext(), list, HomeFragment.this);
                    bind.homeRecycler.setAdapter(adapterBarang);
                    adapterBarang.notifyDataSetChanged();
                    bind.homeNullview.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseGetBarang> call, Throwable t) {
                Toast.makeText(getContext(), "Server Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}