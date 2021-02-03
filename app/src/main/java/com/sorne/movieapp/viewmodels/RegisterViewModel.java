package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.APICallback;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class RegisterViewModel extends ViewModel {
    private String email = "";
    private String password = "";
    private String repeatPassword = "";

    private final UserAuthRepository authRepository;

    @ViewModelInject
    public RegisterViewModel(UserAuthRepository authRepository) {
        this.authRepository = authRepository;
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

    public void registerUser(APICallback<User> callback){
        if(isModelStateValid()){
            authRepository.signUp(email, password, callback);
        }
        else{
            callback.onError("Info not correct");
        }
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
