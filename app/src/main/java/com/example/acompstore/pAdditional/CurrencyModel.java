package com.example.acompstore.pAdditional;

import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyModel {

    private String rupiah;
    private TextView textViewset;

    public CurrencyModel(String rupiah, TextView textViewset) {
        this.rupiah = rupiah;
        this.textViewset = textViewset;

        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        textViewset.setText(formatRupiah.format(Double.parseDouble(rupiah)));
    }
}
