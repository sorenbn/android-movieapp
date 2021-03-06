package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityRegisterBinding;
import com.sorne.movieapp.services.models.User;
import com.sorne.movieapp.services.utils.APICallback;
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
                Log.d("REGISTER", "onClick");
                setLoading(true);
                viewModel.registerUser(new APICallback<User>() {
                    @Override
                    public void onResponse(User response) {
                        if (response != null) {
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
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