package com.sorne.movieapp.network;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAuthAPI {
    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signUp")
    Single<User> signUpEmailPassword(@Query("key") String apiKey, @Body UserAuthRequest authModel);

    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signInWithPassword")
    Single<User> signInEmailPassword(@Query("key") String apiKey, @Body UserAuthRequest authModel);
}
