package com.example.acompstore.pAdditional;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.acompstore.pModel.ModelHomeBarang;
import com.example.acompstore.pModel.ModelPembeliAlamat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SaveAccount {
    private static final String LIST_PEMBELI = "list_pembeli";
    public static void writeDataPembeli(Context context, ModelPembeliAlamat dataPembeli){
        Gson gson = new Gson();
        String jsonString = gson.toJson(dataPembeli);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIST_PEMBELI, jsonString);
        editor.apply();
    }

    public static ModelPembeliAlamat readDataPembeli(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String jsonString = pref.getString(LIST_PEMBELI, "");
        Gson gson = new Gson();
        Type type = new TypeToken<ModelPembeliAlamat>() {}.getType();
        ModelPembeliAlamat dataPembeli = gson.fromJson(jsonString, type);
        return dataPembeli;
    }
}
