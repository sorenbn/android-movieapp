package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.APICallback;

import io.reactivex.rxjava3.core.Flowable;

public interface UserAuthRepository {
    void signUp(String email, String password, APICallback<User> responseCallback);
    Flowable<User> signIn(String email, String password);
}
