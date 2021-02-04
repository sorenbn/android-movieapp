package com.sorne.movieapp.services.network;

import androidx.lifecycle.LiveData;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.network.retrofitAPI.UserAuthAPI;
import com.sorne.movieapp.services.repositories.UserAuthRepository;
import com.sorne.movieapp.services.utils.APICallback;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAuthService implements UserAuthRepository {

    private final UserAuthAPI api;
    private final String apiKey;

    @Inject
    public UserAuthService(UserAuthAPI api, @Named("firebase_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public void signUp(String email, String password, APICallback<User> responseCallback) {
        api.signUpEmailPassword(apiKey, email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        responseCallback.onResponse(response.isSuccessful() ? response.body() : null);
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        responseCallback.onError("Error");
                    }
                });
    }

    @Override
    public Flowable<User> signIn(String email, String password) {
        return api.signInEmailPassword(apiKey, email, password);
    }

/*    @Override
    public void signIn(String email, String password, APICallback<User> responseCallback) {
        api.signInEmailPassword(apiKey, email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        responseCallback.onResponse(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        responseCallback.onError("Error");
                    }
                });
    }*/
}
