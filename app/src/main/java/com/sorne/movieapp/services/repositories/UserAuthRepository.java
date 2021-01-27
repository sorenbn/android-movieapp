package com.sorne.movieapp.services.repositories;

import androidx.lifecycle.MutableLiveData;

import com.sorne.movieapp.services.models.User;

//TODO: Perhaps split interface into UserRepo and AuthenticationRepo
public interface UserAuthRepository {
    MutableLiveData<User> signUp(String email, String password);
    MutableLiveData<User> signIn(String email, String password);
}
