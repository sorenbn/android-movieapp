package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class LoginViewModel extends ViewModel {

    private String email = "";
    private String password = "";

    private final UserAuthRepository authRepo;

    public MutableLiveData<User> userLoginCallback = new MutableLiveData<>();

    @ViewModelInject
    public LoginViewModel(UserAuthRepository authRepo) {
        this.authRepo = authRepo;
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

    public LiveData<User> login() {
        userLoginCallback = authRepo.signIn(email, password);
        return userLoginCallback;
    }
}
