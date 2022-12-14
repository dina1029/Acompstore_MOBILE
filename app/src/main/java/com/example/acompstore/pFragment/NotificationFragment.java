package com.example.acompstore.pFragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.pActivity.DetailTransaksiActivity;
import com.example.acompstore.pAdapter.AdapterNotification;
import com.example.acompstore.pAdditional.AdapterItemClick;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelHistory;
import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pModel.ModelNotification;
import com.example.acompstore.pResponse.ResponseNotification;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pService.ServiceAcount;
import com.example.acompstore.pService.ServiceNotification;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment implements AdapterItemClick {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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

    private ImageView imgnotif;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private List<ModelNotification> modelnotifList = new ArrayList<>();
    private ConstraintLayout nullview;

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        imgnotif = v.findViewById(R.id.img_notif);
        nullview = v.findViewById(R.id.notif_nullview);

        nullview.setVisibility(View.GONE);
        retrieveData();

        shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean("homeId", false);
        editor.commit();

        rvData = v.findViewById(R.id.rec_notif);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    public void retrieveData() {
        String idPembeli = SaveAccount.readDataPembeli(getActivity()).getIdPembeli();
        ServiceNotification service = Apiretro.getService().create(ServiceNotification.class);
        Call<ResponseNotification> tampilNotifikasi = service.getNotifikasi(idPembeli);
        tampilNotifikasi.enqueue(new Callback<ResponseNotification>() {
            @Override
            public void onResponse(Call<ResponseNotification> call, Response<ResponseNotification> response) {
                if (response.body().getKode() == 200) {
                    modelnotifList = response.body().getData();
                    adData = new AdapterNotification(getContext(), modelnotifList, NotificationFragment.this);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();
                    nullview.setVisibility(View.GONE);
                }else{
                    nullview.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseNotification> call, Throwable t) {
                Toast.makeText(getContext(), "Server error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

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
                ft.replace(R.id.home_frmlayout, new NotificationFragment());
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
    public void onItemClick(int position) {
        ServiceNotification service = Apiretro.getService().create(ServiceNotification.class);
        Call<ResponsePosMessage> ubahNotif = service.setOneClick(modelnotifList.get(position).getIdUpdateBeli());
        ubahNotif.enqueue(new Callback<ResponsePosMessage>() {
            @Override
            public void onResponse(Call<ResponsePosMessage> call, Response<ResponsePosMessage> response) {
                if (response.body().getKode()==1) {
                    shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
                    SharedPreferences.Editor editor = shared.edit();
                    editor.putBoolean("homeId", true);
                    editor.commit();
                }
            }

            @Override
            public void onFailure(Call<ResponsePosMessage> call, Throwable t) {

            }
        });
        Intent intent = new Intent(getActivity(), DetailTransaksiActivity.class);
        intent.putExtra("idBeli", modelnotifList.get(position).getKodeUpdateBeli());
        startActivity(intent);
    }
}