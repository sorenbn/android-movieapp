package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.repositories.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    public LiveData<MovieListResponse> popularMovieCallback;

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        popularMovieCallback = getPopularMoviesLiveData();
    }

    public void fetchPopularMovies(){
        getPopularMoviesLiveData();
    }

    private LiveData<MovieListResponse> getPopularMoviesLiveData(){
        return Transformations.map(movieRepository.getPopularMovies(), input -> {
            return input;
        });
    }
}
