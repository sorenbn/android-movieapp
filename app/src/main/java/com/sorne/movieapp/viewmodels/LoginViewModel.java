package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.repositories.UserAuthRepository;
import com.sorne.movieapp.services.utils.AsyncResource;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private String email = "";
    private String password = "";

    private final UserAuthRepository authRepo;

    private MediatorLiveData<AsyncResource<User>> userSignIn = new MediatorLiveData<>();

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

    public void login() {
        userSignIn.setValue(AsyncResource.loading(null));

        final LiveData<AsyncResource<User>> response = LiveDataReactiveStreams
                .fromPublisher(authRepo.signIn(email, password)
                .onErrorReturn(throwable -> {
                    User userError = new User("-1", "-1", "-1");
                    return userError;
                })
                .map((Function<User, AsyncResource<User>>) user -> {
                    if(user.getUserId() == "-1"){
                        return AsyncResource.error("Couldn't log in", null);
                    }

                    return AsyncResource.success(user);
                })
                .subscribeOn(Schedulers.io()));

        userSignIn.addSource(response, userAsyncResource -> {
            userSignIn.setValue(userAsyncResource);
            userSignIn.removeSource(response);
        });
    }

    public LiveData<AsyncResource<User>> observeUserLogin() {
        return userSignIn;
    }
}
