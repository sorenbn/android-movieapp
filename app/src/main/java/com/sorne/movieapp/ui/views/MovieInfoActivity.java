package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityMovieInfoBinding;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.viewmodels.MovieInfoViewModel;

public class MovieInfoActivity extends AppCompatActivity {

    private MovieInfoViewModel viewModel;
    private ActivityMovieInfoBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);

        viewModel = new ViewModelProvider(this).get(MovieInfoViewModel.class);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_info);
        dataBinding.setViewmodel(viewModel);

        //TODO: Fix this. Feels dirty
        Movie movie = getIntent().getParcelableExtra("movie");
        viewModel.setMovie(movie);
    }
}