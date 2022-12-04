package com.example.acompstore.pFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.pAdapter.AdapterNotification;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pModel.ModelNotification;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pService.ServiceAcount;
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
public class NotificationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private List<ModelNotification> modelnotifList = new ArrayList<>();


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

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        retrieveData();

        rvData = view.findViewById(R.id.rec_notif);
        rvData.setHasFixedSize(true);
        rvData.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void retrieveData() {
        ServiceAcount service1 = Apiretro.getService().create(ServiceAcount.class);
        Call<ResponsePos> tampilData = service1.setNotif();
        tampilData.enqueue(new Callback<ResponsePos>() {
            @Override
            public void onResponse(Call<ResponsePos> call, Response<ResponsePos> response) {
                byte kode = response.body().getKode();

                modelnotifList = response.body().getData();
                Gson gson = new Gson();
                System.out.println(gson.toJson(modelnotifList) + " JSON DATA");
                adData = new AdapterNotification(getContext(), modelnotifList);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponsePos> call, Throwable t) {
                Toast.makeText(getActivity(), "Server Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}