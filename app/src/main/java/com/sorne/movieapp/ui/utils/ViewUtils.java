package com.sorne.movieapp.ui.utils;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sorne.movieapp.R;

public class ViewUtils {
    public static void loadImage(ImageView imageView, String url){
        RequestOptions options = new RequestOptions()
                .placeholder(ViewUtils.getProgressDrawable(imageView.getContext()))
                .error(R.mipmap.ic_launcher_round);
        Glide.with(imageView.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(imageView);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable drawable = new CircularProgressDrawable(context);
        drawable.setStrokeWidth(10.0f);
        drawable.setCenterRadius(50.0f);
        drawable.setBackgroundColor(0xFFFFFFFF);
        drawable.start();

        return drawable;
    }
}