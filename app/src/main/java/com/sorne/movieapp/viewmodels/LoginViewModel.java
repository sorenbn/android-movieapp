package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class LoginViewModel extends ViewModel {

    private String email = "";
    private String password = "";

    private final UserAuthRepository authRepo;

    public LiveData<User> userLoginCallback;

    @ViewModelInject
    public LoginViewModel(UserAuthRepository authRepo) {
        this.authRepo = authRepo;
        userLoginCallback = tryLogin("", "");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(){
        tryLogin(email, password);
    }

    private LiveData<User> tryLogin(String email, String password){
        return Transformations.map(authRepo.signIn(email, password), input -> {
           return input;
        });
    }
}
