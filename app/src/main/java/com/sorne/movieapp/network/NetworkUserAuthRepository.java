package com.sorne.movieapp.network;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;
import com.sorne.movieapp.repositories.UserAuthRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Single;

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
    public Single<User> signIn(UserAuthRequest authModel) {
        return api.signInEmailPassword(apiKey, authModel);
    }
}
