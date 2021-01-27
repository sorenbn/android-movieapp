package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class RegisterViewModel extends ViewModel {
    private String email = "";
    private String password = "";
    private String repeatPassword = "";

    private final UserAuthRepository authRepository;

    private MutableLiveData<User> userRegisterCallback = new MutableLiveData<>();

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

    public LiveData<User> getUserRegisterCallback() {
        return userRegisterCallback;
    }

    public LiveData<User> registerUser() {
        if (isModelStateValid()) {
            userRegisterCallback = authRepository.signUp(email, password);
        }
        else{
            userRegisterCallback.setValue(null);
        }

        return userRegisterCallback;
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
