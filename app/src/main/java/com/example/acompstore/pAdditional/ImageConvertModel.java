package com.example.acompstore.pAdditional;

import android.content.Context;
import android.widget.ImageView;

import com.example.acompstore.pConnection.Apiretro;
import com.squareup.picasso.Picasso;

public class ImageConvertModel {

    Context ctx;
    String url;
    ImageView imageViewiewgambar;

    public ImageConvertModel(Context ctx, String url, ImageView textViewgambar) {
        this.ctx = ctx;
        this.url = url;
        this.imageViewiewgambar = textViewgambar;
    }

    public void ubahGambar(){
        Picasso.with(ctx).
                load(Apiretro.base_url+"images/"+url)
                .fit()
                .centerInside()
                .into(imageViewiewgambar);
    }
}
