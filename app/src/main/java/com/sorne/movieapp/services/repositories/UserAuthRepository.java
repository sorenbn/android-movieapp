package com.sorne.movieapp.services.repositories;

import androidx.lifecycle.MutableLiveData;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.models.UserAuthRequest;

//TODO: Perhaps split interface into UserRepo and AuthenticationRepo
public interface UserAuthRepository {
    MutableLiveData<User> signUp(UserAuthRequest authModel);
    MutableLiveData<User> signIn(UserAuthRequest authModel);
}
