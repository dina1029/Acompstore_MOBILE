package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponseNotification;
import com.example.acompstore.pResponse.ResponsePosMessage;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceNotification {
    @FormUrlEncoded
    @POST("notification")
    Call<ResponseNotification> getNotifikasi(
            @Field("idPembeli") String idPembeli
    );

    @FormUrlEncoded
    @POST("updatenotifikasi")
    Call<ResponsePosMessage> setOneClick(@Field("idUpdateBeli") String idUpdateBeli);
}
