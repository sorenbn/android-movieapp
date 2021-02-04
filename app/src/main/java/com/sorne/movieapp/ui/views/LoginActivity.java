package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityLoginBinding;
import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.AsyncResource;
import com.sorne.movieapp.viewmodels.LoginViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private ActivityLoginBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBindings();
        setupObservers();
    }

    private void setupBindings() {
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        dataBinding.setViewmodel(viewModel);
    }

    private void setupObservers() {
        viewModel.observeUserLogin().observe(this, userAsyncResource -> {
            handleSignInResponse(userAsyncResource);
        });

        dataBinding.loginBtnLogin.setOnClickListener(v -> {
            viewModel.login();
        });

        dataBinding.loginBtnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        dataBinding.loginBtnSkip.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    private void handleSignInResponse(AsyncResource<User> userAsyncResource) {
        if(userAsyncResource != null){
            switch(userAsyncResource.status){
                case LOADING:
                    Log.d("SIGN IN", "loading");
                    setLoading(true);
                    break;

                case SUCCESS:
                    Log.d("SIGN IN", "success");
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    break;

                case ERROR:
                    Log.d("SIGN IN", "error");
                    setLoading(false);
                    break;
            }
        }
    }

    private void setLoading(boolean loading) {
        dataBinding.loginProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);

        dataBinding.loginBtnLogin.setEnabled(!loading);
        dataBinding.loginBtnRegister.setEnabled(!loading);
        dataBinding.loginEmail.setEnabled(!loading);
        dataBinding.loginPassword.setEnabled(!loading);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setLoading(false);
    }
}