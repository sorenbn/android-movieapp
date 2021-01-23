package com.sorne.movieapp.di;

import android.content.Context;

import com.sorne.movieapp.R;
import com.sorne.movieapp.network.MovieAPI;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class APIModule {

    @Provides
    @Named("base_url")
    public String provideBaseURL(@ApplicationContext Context context){
        return context.getString(R.string.movie_api_base_url);
    }

    @Provides
    @Named("api_key")
    public String provideAPIKey(@ApplicationContext Context context){
        return context.getString(R.string.movie_api_key);
    }

    @Provides
    @Singleton
    public MovieAPI provideAPI(@Named("base_url") String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(MovieAPI.class);
    }
}
