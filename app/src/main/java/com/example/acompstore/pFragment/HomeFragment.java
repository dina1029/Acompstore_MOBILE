package com.example.acompstore.pFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.FragmentHomeBinding;
import com.example.acompstore.pActivity.HomeSearchActivity;
import com.example.acompstore.pAdapter.AdapterHomeBarang;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pResponse.ResponseGetBarang;
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
public class HomeFragment extends Fragment implements AdapterItemClick {

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
    private AdapterHomeBarang adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        View view = bind.getRoot();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        bind.homeRecycler.setLayoutManager(gridLayoutManager);
        bind.homeRecycler.setNestedScrollingEnabled(false);
        retrieve_data(view);

        bind.homeBtcari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HomeSearchActivity.class));
            }
        });

        return view;
    }
    private View retrieve_data(View view){
        ServiceHome service = Apiretro.getService().create(ServiceHome.class);
        Call<ResponseGetBarang> tampildata = service.getHomeBarangNoDiskon();
        tampildata.enqueue(new Callback<ResponseGetBarang>() {
            @Override
            public void onResponse(Call<ResponseGetBarang> call, Response<ResponseGetBarang> response) {
                if(response.body().getKode()==1) {
                    list = response.body().getData();
                    adapter = new AdapterHomeBarang(view.getContext(), list, HomeFragment.this);
                    bind.homeRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
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

    @Override
    public void onItemClick(int position) {

    }
}