package com.sorne.movieapp.services.network;

import androidx.lifecycle.MutableLiveData;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.models.UserAuthRequest;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUserAuthRepository implements UserAuthRepository {

    private final UserAuthAPI api;
    private final String apiKey;

    @Inject
    public NetworkUserAuthRepository(UserAuthAPI api, @Named("firebase_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public MutableLiveData<User> signUp(UserAuthRequest authModel) {
        MutableLiveData<User> userResponse = new MutableLiveData<>();

        api.signUpEmailPassword(apiKey, authModel)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userResponse.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        userResponse.setValue(null);
                    }
                });

        return userResponse;
    }

    @Override
    public MutableLiveData<User> signIn(UserAuthRequest authModel) {
        MutableLiveData<User> userResponse = new MutableLiveData<>();

        api.signInEmailPassword(apiKey, authModel)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userResponse.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        userResponse.setValue(null);
                    }
                });

        return userResponse;
    }
}