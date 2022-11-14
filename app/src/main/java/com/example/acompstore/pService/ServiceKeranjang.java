package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePostPembeli;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceKeranjang {

    @FormUrlEncoded
    @POST("keranjang.php")
    Call<ResponsePos> addToCart(
            @Field("Keranjang_idKategori") String Keranjang_idKategori,
            @Field("Keranjang_idPembeli") String Keranjang_idPembeli,
            @Field("KeranjangJumlah") String KeranjangJumlah
    );
}
