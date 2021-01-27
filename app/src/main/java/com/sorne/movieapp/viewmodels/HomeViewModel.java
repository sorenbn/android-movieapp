package com.sorne.movieapp.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.repositories.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public LiveData<MovieListResponse> popularMovieResponse;

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        popularMovieResponse = popularMoviesLiveData();
    }

    public void fetchPopularMovies(){
        Log.d("FETCH", "fetching movies..");
        popularMoviesLiveData();
    }

    private LiveData<MovieListResponse> popularMoviesLiveData(){
        return Transformations.map(movieRepository.getPopularMovies(), input -> {
            Log.d("FETCH", "Retrieved data: " + input.getMovies().size());
            return input;
        });
    }
}
