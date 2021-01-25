package com.sorne.movieapp.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;
import com.sorne.movieapp.repositories.UserAuthRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Single;
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
    public Single<User> signUp(UserAuthRequest authModel) {
        return api.signUpEmailPassword(apiKey, authModel);
    }

    @Override
    public LiveData<User> signIn(UserAuthRequest authModel) {
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
        //return api.signInEmailPassword(apiKey, authModel);
    }
}
