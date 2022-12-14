package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePosJumlah;
import com.example.acompstore.pResponse.ResponsePosMessage;
import com.example.acompstore.pResponse.ResponseTest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceCheckout {

    @FormUrlEncoded
    @POST("transaction")
    Call<ResponsePosJumlah> setTransaction(
            @Field("TotalBeli") String TotalBeli,
            @Field("Status") String Status,
            @Field("Beli_idPembeli") String Beli_idPembeli,
            @Field("Jumlah[]") String[] Jumlah,
            @Field("DetailHarga[]") String[] DetailHarga,
            @Field("DetailDiskon[]") String[] DetailDiskon,
            @Field("Detbel_idKategori[]") String[] Detbel_idKategori,
            @Field("JasaKurir") String JasaKurir,
            @Field("ServiceKurir") String ServiceKurir,
            @Field("BiayaOngkir") String BiayaOngkir,
            @Field("EstimasiWaktu") String EstimasiWaktu
    );
}
