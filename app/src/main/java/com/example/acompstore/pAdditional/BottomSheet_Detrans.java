package com.example.acompstore.pAdditional;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import com.example.acompstore.R;
import com.example.acompstore.pConnection.Apiretro;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pService.ServiceTransaksi;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheet_Detrans extends BottomSheetDialogFragment {

    private BottomSheetBehavior sheetBehavior;
    private AppCompatButton btn_konfir;
    private RadioButton selectedRadio;
    private RadioGroup rd_group;
    private String idBeli;
    private SharedPreferences shared;


    public BottomSheet_Detrans(String idBeli) {
        this.idBeli = idBeli;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view1 = View.inflate(getContext(), R.layout.bottomsheet_detailtrans, null);

        rd_group = view1.findViewById(R.id.sheetdetrans_radiogroup);

        btn_konfir = view1.findViewById(R.id.sheetdetrans_btconfirm);
        btn_konfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadioButton = rd_group.getCheckedRadioButtonId();
                selectedRadio = view1.findViewById(selectedRadioButton);
                String alasanDibatalkan = selectedRadio.getText().toString();
                ServiceTransaksi service = Apiretro.getService().create(ServiceTransaksi.class);
                Call<ResponsePosMessage> setBatal = service.setBatalkanTransaksi(idBeli, alasanDibatalkan);
                setBatal.enqueue(new Callback<ResponsePosMessage>() {
                    @Override
                    public void onResponse(Call<ResponsePosMessage> call, Response<ResponsePosMessage> response) {
                        byte kode = response.body().getKode();
                        if (kode == 1){
                            Toast.makeText(getContext(), "Transaksi Telah Dibatalkan", Toast.LENGTH_SHORT).show();
                            shared = getActivity().getSharedPreferences("myapp-data", MODE_PRIVATE);
                            SharedPreferences.Editor editor = shared.edit();
                            editor.putBoolean("homeId", true);
                            editor.commit();
                            getActivity().onBackPressed();

                        }else{
                            Toast.makeText(getContext(), "Gagal Membatalkan Transaksi", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponsePosMessage> call, Throwable t) {
                        Toast.makeText(getContext(), "Server error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        dialog.setContentView(view1);
        sheetBehavior = BottomSheetBehavior.from((View) view1.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
