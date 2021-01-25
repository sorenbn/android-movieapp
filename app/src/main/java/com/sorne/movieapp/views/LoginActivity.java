package com.sorne.movieapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
        viewModel.getLoading().observe(this, loading -> {
            Log.d("LOGIN", "loading: " + loading.toString());
        });

        viewModel.getLoginSuccess().observe(this, success -> {
            if(success){
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}