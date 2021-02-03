package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityLoginBinding;
import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.APICallback;
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
        dataBinding.loginBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading(true);
                viewModel.login(new APICallback<User>() {
                    @Override
                    public void onResponse(User response) {
                        if (response != null) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            return;
                        }

                        setLoading(false);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        setLoading(false);
                    }
                });
            }
        });

        dataBinding.loginBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        dataBinding.loginBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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