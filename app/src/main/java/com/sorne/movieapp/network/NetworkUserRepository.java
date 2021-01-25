package com.sorne.movieapp.network;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthModel;
import com.sorne.movieapp.repositories.UserRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Single;

public class NetworkUserRepository implements UserRepository {

    private final UserAPI api;
    private final String apiKey;

    @Inject
    public NetworkUserRepository(UserAPI api, @Named("firebase_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public Single<User> signUp(UserAuthModel authModel) {
        return api.signUpEmailPassword(apiKey, authModel);
    }

    @Override
    public Single<User> signIn(UserAuthModel authModel) {
        return api.signInEmailPassword(apiKey, authModel);
    }
}
