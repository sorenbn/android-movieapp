package com.sorne.movieapp.services.network;

import androidx.lifecycle.MutableLiveData;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkUserAuthRepository implements UserAuthRepository {

    private final UserAuthAPI api;
    private final String apiKey;

    private MutableLiveData<User> userSignUpResponse = new MutableLiveData<>();
    private MutableLiveData<User>userSignInResponse =new MutableLiveData<>();

    @Inject
    public NetworkUserAuthRepository(UserAuthAPI api, @Named("firebase_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public MutableLiveData<User> signUp(String email, String password) {
        api.signUpEmailPassword(apiKey, email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userSignUpResponse.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        userSignUpResponse.postValue(null);
                    }
                });

        return userSignUpResponse;
    }

    @Override
    public MutableLiveData<User> signIn(String email, String password) {
        api.signInEmailPassword(apiKey, email, password)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        userSignInResponse.postValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        userSignInResponse.postValue(null);
                    }
                });

        return userSignInResponse;
    }
}
