package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.models.UserAuthRequest;
import com.sorne.movieapp.services.repositories.UserAuthRepository;

public class LoginViewModel extends ViewModel {

    private UserAuthRequest userRequestModel = new UserAuthRequest("", "");
    private final UserAuthRepository authRepo;

    private LiveData<User> userSignIn = new MutableLiveData<>();

    @ViewModelInject
    public LoginViewModel(UserAuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    public UserAuthRequest getUserRequestModel() {
        return userRequestModel;
    }

    public LiveData<User> login() {
        userSignIn = authRepo.signIn(userRequestModel);
        return userSignIn;
    }
}
