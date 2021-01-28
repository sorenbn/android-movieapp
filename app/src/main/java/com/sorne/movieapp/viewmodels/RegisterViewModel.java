package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class RegisterViewModel extends ViewModel {
    private String email = "";
    private String password = "";
    private String repeatPassword = "";

    private final UserAuthRepository authRepository;

    public LiveData<User> userRegisterCallback;

    @ViewModelInject
    public RegisterViewModel(UserAuthRepository authRepository) {
        this.authRepository = authRepository;
        userRegisterCallback = tryRegisterUser("", "");
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void registerUser(){
        //if(isModelStateValid()){
            tryRegisterUser(email, password);
        //}
    }

    private LiveData<User> tryRegisterUser(String email, String password) {
        return Transformations.map(authRepository.signUp(email, password), input -> {
            return input;
        });
    }

    private boolean isModelStateValid() {
        if (!email.contains("@")) {
            return false;
        } else if (!password.equals(repeatPassword)) {
            return false;
        }

        return true;
    }
}
