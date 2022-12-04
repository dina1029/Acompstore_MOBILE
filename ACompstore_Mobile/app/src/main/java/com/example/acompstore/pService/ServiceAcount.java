package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponsePos;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ServiceAcount {

    @FormUrlEncoded
    @POST("alert_ubahakun.php")
    Call<ResponsePos> setUbahAkun(
            @Query("idPembeli") String idPembeli,
            @Field("NamaPembeli") String namaPembeli,
            @Field("NoHPPembeli") String noHP,
            @Field("PasswordPembeli") String pasword
    );

    @FormUrlEncoded
    @POST("alert_ubahpassword.php")
    Call<ResponsePos> setUbahKeamanan(
            @Query("idPembeli") String idPembeli,
            @Field("PasswordPembeli") String passwordpembeli
    );

    @FormUrlEncoded
    @POST("alert_ubahalamat.php")
    Call<ResponsePos> setUbahAlamat(
            @Query("Alamat_idPembeli") String idPembeli,
            @Field("Kota") String kota,
            @Field("Kecamatan")String kec,
            @Field("Address")String alamat
    );

    @GET("notification.php")
    Call<ResponsePos> setNotif(

    );

}
