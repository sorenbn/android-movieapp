package com.sorne.movieapp.network;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthModel;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserAPI {
    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signUp")
    Single<User> signUpEmailPassword(@Query("key") String apiKey, @Body UserAuthModel authModel);

    //Uses ./ to escape the ':' in the url
    @POST("./accounts:signInWithPassword")
    Single<User> signInEmailPassword(@Query("key") String apiKey, @Body UserAuthModel authModel);
}
