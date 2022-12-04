package com.example.acompstore.pConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Apiretro {
    public static final String public_idPembeli= "1";
        public static String base_url = "http://172.16.109.209/AcompStore/";
    private static Retrofit retro;

    public static Retrofit getService(){

        if(retro == null){
            retro = new Retrofit.Builder().baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retro;
    }
}
