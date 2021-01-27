package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityLoginBinding;
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

        viewModel.userLoginCallback.observe(LoginActivity.this, user -> {
            if (user != null) {
                Log.d("LOGIN", "User Login");
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
            } else {
                setLoading(false);
                Log.d("LOGIN", "User FAILED Login");
            }
        });

        dataBinding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading(true);
                Log.d("LOGIN", "onClick");
                viewModel.login();
            }
        });

        dataBinding.loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
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