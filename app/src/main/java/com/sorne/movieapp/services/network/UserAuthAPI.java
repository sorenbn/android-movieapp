package com.sorne.movieapp.services.network;

import com.sorne.movieapp.services.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAuthAPI {
    //Uses ./ to escape the ':' in the url
    @FormUrlEncoded
    @POST("./accounts:signUp")
    Call<User> signUpEmailPassword(@Query("key") String apiKey, @Field("email") String email, @Field("password") String password);

    //Uses ./ to escape the ':' in the url
    @FormUrlEncoded
    @POST("./accounts:signInWithPassword")
    Call<User> signInEmailPassword(@Query("key") String apiKey, @Field("email") String email, @Field("password") String password);
}
