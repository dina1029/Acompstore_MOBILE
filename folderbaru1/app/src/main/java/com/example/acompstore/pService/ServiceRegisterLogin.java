package com.example.acompstore.pService;

import com.example.acompstore.pResponse.ResponsePostPembeli;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ServiceRegisterLogin {

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponsePostPembeli> setLogin(
            @Field("EmailPembeli") String EmailPembeli,
            @Field("PasswordPembeli") String PasswordPembeli
    );
    @FormUrlEncoded
    @POST("registerpembeli.php")
    Call<ResponsePostPembeli> registerPembeli(
            @Field("EmailPembeli") String EmailPembeli,
            @Field("NamaPembeli") String NamaPembeli,
            @Field("NoHPPembeli") String NoHPPembeli,
            @Field("PasswordPembeli") String PasswordPembeli,
            @Field("Kota") String Kota,
            @Field("Kecamatan") String Kecamatan,
            @Field("Address") String Address
    );
}
