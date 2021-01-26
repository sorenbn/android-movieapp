package com.sorne.movieapp.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.repositories.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MovieListResponse> getPopularMovies(){
        return movieRepository.getPopularMovies();
    }

    public void fetchData(){
        Log.d("HOME", "fetchData: fetching data from repo");
        movieRepository.getPopularMovies();
    }
}
