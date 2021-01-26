package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityRegisterBinding;
import com.sorne.movieapp.viewmodels.RegisterViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RegisterActivity extends AppCompatActivity {

    private RegisterViewModel viewModel;
    private ActivityRegisterBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupBindings();
        setupObservers();
    }

    private void setupBindings() {
        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        dataBinding.setViewmodel(viewModel);
    }

    private void setupObservers() {
        dataBinding.registerBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLoading(true);

                viewModel.registerUser().observe(RegisterActivity.this, user -> {
                    if(user != null){
                        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }else {
                        setLoading(false);
                    }
                });
            }
        });
    }

    private void setLoading(boolean loading) {
        dataBinding.registerProgressBar.setVisibility(loading ? View.VISIBLE : View.GONE);

        dataBinding.registerEmail.setEnabled(!loading);
        dataBinding.registerPassword.setEnabled(!loading);
        dataBinding.registerPasswordRepeat.setEnabled(!loading);
        dataBinding.registerBtnRegister.setEnabled(!loading);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setLoading(false);
    }
}