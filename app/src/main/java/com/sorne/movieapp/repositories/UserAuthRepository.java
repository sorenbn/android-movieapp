package com.sorne.movieapp.repositories;

import androidx.lifecycle.LiveData;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;

import io.reactivex.rxjava3.core.Single;

//TODO: Perhaps split interface into UserRepo and AuthenticationRepo
public interface UserAuthRepository {
    Single<User> signUp(UserAuthRequest authModel);
    LiveData<User> signIn(UserAuthRequest authModel);
}
