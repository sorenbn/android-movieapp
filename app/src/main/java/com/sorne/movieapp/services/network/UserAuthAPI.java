package com.sorne.movieapp.services.network;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.models.UserAuthRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAuthAPI {
    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signUp")
    Call<User> signUpEmailPassword(@Query("key") String apiKey, @Body UserAuthRequest authModel);

    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signInWithPassword")
    Call<User> signInEmailPassword(@Query("key") String apiKey, @Body UserAuthRequest authModel);
}
