package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityHomeBinding;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.network.APICallback;
import com.sorne.movieapp.ui.adaptors.MovieListAdaptor;
import com.sorne.movieapp.viewmodels.HomeViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    private HomeViewModel viewModel;
    private ActivityHomeBinding dataBinding;
    private MovieListAdaptor movieListAdaptor = new MovieListAdaptor(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBindings();
        setupObservers();

        fetchMovies();
    }

    private void setupBindings() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        dataBinding.recyclerMovieList.setAdapter(movieListAdaptor);
    }

    private void setupObservers(){
        dataBinding.homeFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchMovies();
            }
        });
    }

    private void fetchMovies(){
        dataBinding.recyclerMovieList.setVisibility(View.GONE);
        viewModel.getPopularMovies(new APICallback<MovieListResponse>() {
            @Override
            public void onResponse(MovieListResponse response) {
                movieListAdaptor.updateData(response.getMovies());
                dataBinding.recyclerMovieList.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("MOVIE", "onError: " + errorMessage);
            }
        });
    }
}