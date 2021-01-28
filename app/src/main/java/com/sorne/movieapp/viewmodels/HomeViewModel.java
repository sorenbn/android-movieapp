package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.network.APICallback;
import com.sorne.movieapp.services.repositories.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void getMovieDetails(int id, APICallback<Movie> callback){
        movieRepository.getMovieDetails(id, callback);
    }

    public void getPopularMovies(APICallback<MovieListResponse> callback){
        movieRepository.getPopularMovies(callback);
    }
}
