package com.sorne.movieapp.ui.bindingadaptors;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.sorne.movieapp.R;
import com.sorne.movieapp.ui.utils.ViewUtils;

public class BindingAdaptors {

    @BindingAdapter("imageFromUrl")
    public static void loadImageUrl(ImageView view, String url){
        if(url != null && !url.isEmpty()){
            //TODO: Get base URL Injected somehow
            String actualUrl = view.getContext().getString(R.string.movie_api_base_url_images) + url;
            ViewUtils.loadImage(view, actualUrl);
        }
    }
}
