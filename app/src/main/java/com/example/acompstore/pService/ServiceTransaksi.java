package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseGetDetailBeli;
import com.example.acompstore.pResponse.ResponsePosMessage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceTransaksi {

    @GET("detailtransaksi")
    Call<ResponseGetDetailBeli> getDetailBeli(@Query("idBeli") String idBeli);

    @FormUrlEncoded
    @POST("batalkanpesanan")
    Call<ResponsePosMessage> setBatalkanTransaksi(
            @Field("idBeli") String idBeli,
            @Field("Bapes_Alasan") String Bapes_Alasan
    );
}
