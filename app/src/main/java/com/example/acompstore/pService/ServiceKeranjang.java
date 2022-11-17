package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseGetKeranjang;
import com.example.acompstore.pResponse.ResponsePos;
import com.example.acompstore.pResponse.ResponsePosMessage;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceKeranjang {

    @FormUrlEncoded
    @POST("keranjang.php")
    Call<ResponsePosMessage> addToCart(
            @Field("Keranjang_idKategori") String Keranjang_idKategori,
            @Field("Keranjang_idPembeli") String Keranjang_idPembeli,
            @Field("KeranjangJumlah") String KeranjangJumlah
    );
    @GET("keranjang.php")
    Call<ResponseGetKeranjang> getKeranjang();

    @DELETE("Keranjang.php")
    Call<ResponsePos> deleteKeranjang(@Query("idKeranjang") String idKeranjang);

    @FormUrlEncoded
    @POST("keranjangcount.php")
    Call<ResponsePosMessage> setJumlahKeranjang(
            @Query("idKeranjang") String idKeranjang,
            @Field("KeranjangJumlah") String KeranjangJumlah
    );
}
