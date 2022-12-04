package com.example.acompstore.pFragment;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acompstore.R;
import com.example.acompstore.databinding.ActivityLoginBinding;
import com.example.acompstore.pActivity.LoginActivity;
import com.example.acompstore.pActivity.SettingAkunActivity;
import com.example.acompstore.pActivity.SettingAlamatActivity;
import com.example.acompstore.pActivity.SettingKeamananActivity;
import com.example.acompstore.pAdditional.SaveAccount;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;
    private ImageView nullic;
    private TextView nullhead1;
    private TextView nullhead2;
    private AppCompatButton nulbtyes;
    private AppCompatButton nullbtno;
    private AlertDialog alert;
    TextView email1, usname1;


    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
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
    private SharedPreferences shared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ImageButton btn_1, btn_2;
        Button btn1, btn2, btn3, btn4;



        // Inflate the layout for this fragment
//        btn_1= (ImageButton) view.findViewById(R.id.btn_ker);
//        btn_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        email1 = view.findViewById(R.id.tam_emailfr);
        email1.setText(SaveAccount.readDataPembeli(getActivity()).getEmailPembeli());

        usname1 = view.findViewById(R.id.tam_namafr);
        usname1.setText(SaveAccount.readDataPembeli(getActivity()).getNamaPembeli());

        btn_2 = (ImageButton) view.findViewById(R.id.btn_cht);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wpurl = "https://wa.me/+6281333793691?text=Saya memiliki keluhan.....";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(wpurl));
                startActivity(intent);
            }
        });

        btn1 = (Button) view.findViewById(R.id.btn_akun);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingAkunActivity.class));
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn_kmn);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingKeamananActivity.class);
                SettingFragment.this.startActivity(i);
            }
        });

        btn3 = (Button) view.findViewById(R.id.btn_as);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SettingAlamatActivity.class));
            }
        });

        btn4 = (Button) view.findViewById(R.id.btn_logout);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertLogOut();
            }
        });
        return view;
    }

    @SuppressLint("ResourceType")
    private void alertLogOut() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.activity_logout, null);
        alertDialogBuilder.setView(view);
        alert = alertDialogBuilder.create();
        alert.show();
        nullic = view.findViewById(R.drawable.il_logout);
        nullhead1 = view.findViewById(R.id.head1);
        nullhead2 = view.findViewById(R.id.head2);
        nulbtyes = view.findViewById(R.id.btn_yes);
        nulbtyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putBoolean("status", false);
                editor.putString("idPembeli", null);
                editor.commit();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getActivity(), "Berhasil Log Out", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });
        nullbtno = view.findViewById(R.id.btn_no);
        nullbtno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.cancel();
            }
        });
    }
}