package com.sorne.movieapp.di;

import android.content.Context;

import com.sorne.movieapp.R;
import com.sorne.movieapp.network.UserAPI;

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
public class UserAPIModule {

    @Provides
    @Named("firebase_base_url")
    public String provideBaseURL(@ApplicationContext Context context){
        return context.getString(R.string.firebase_base_url);
    }

    @Provides
    @Named("firebase_api_key")
    public String provideAPIKey(@ApplicationContext Context context){
        return context.getString(R.string.firebase_api_key);
    }

    @Provides
    @Singleton
    public UserAPI provideUserAPI(@Named("firebase_base_url") String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(UserAPI.class);
    }
}
