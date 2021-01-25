package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;
import com.sorne.movieapp.repositories.UserAuthRepository;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class LoginViewModel extends ViewModel {

    private UserAuthRequest userRequestModel = new UserAuthRequest("", "");
    private CompositeDisposable disposable = new CompositeDisposable();
    private UserAuthRepository authRepo;

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

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
