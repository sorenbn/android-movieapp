package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.APICallback;

public interface UserAuthRepository {
    void signUp(String email, String password, APICallback<User> responseCallback);
    void signIn(String email, String password, APICallback<User> responseCallback);
}
