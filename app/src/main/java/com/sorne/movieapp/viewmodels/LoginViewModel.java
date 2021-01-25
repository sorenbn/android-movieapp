package com.sorne.movieapp.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.models.User;
import com.sorne.movieapp.models.UserAuthRequest;
import com.sorne.movieapp.repositories.UserAuthRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginSuccess = new MutableLiveData<>();

    private UserAuthRequest userRequestModel = new UserAuthRequest("", "");
    private CompositeDisposable disposable = new CompositeDisposable();
    private UserAuthRepository authRepo;

    @ViewModelInject
    public LoginViewModel(UserAuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }

    public LiveData<Boolean> getLoginSuccess() {
        return loginSuccess;
    }

    public UserAuthRequest getUserRequestModel() {
        return userRequestModel;
    }

    public void onLoginPressed() {
        loading.setValue(true);

        disposable.add(authRepo.signIn(userRequestModel)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableSingleObserver<User>() {
            @Override
            public void onSuccess(@NonNull User user) {
                Log.d("LOGIN", "onSuccess: " + user.toString());
                loading.setValue(false);
                loginSuccess.setValue(true);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("LOGIN", "onFailed: " + e.toString());
                loading.setValue(false);
                loginSuccess.setValue(false);
            }
        }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
