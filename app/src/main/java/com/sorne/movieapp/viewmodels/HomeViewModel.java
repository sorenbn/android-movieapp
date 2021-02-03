package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.enums.MovieListType;
import com.sorne.movieapp.services.utils.APICallback;
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

    public void getMovieList(MovieListType type, APICallback<MovieListResponse> callback){
        switch (type){
            case Popular:
                movieRepository.getPopularMovies(callback);
                break;

            case TopRated:
                movieRepository.getTopRatedMovies(callback);
                break;
        }
    }

    public void getMovieGenres(APICallback<GenreListResponse> callback){
        movieRepository.getAllMovieGenres(callback);
    }
}