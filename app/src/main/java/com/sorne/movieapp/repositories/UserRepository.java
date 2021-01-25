package com.sorne.movieapp.repositories;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthModel;

import io.reactivex.rxjava3.core.Single;

//TODO: Perhaps split interface into UserRepo and AuthenticationRepo
public interface UserRepository {
    Single<User> signUp(UserAuthModel authModel);
    Single<User> signIn(UserAuthModel authModel);
}
