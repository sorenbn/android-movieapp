package com.sorne.movieapp.viewmodels;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.enums.MovieListType;
import com.sorne.movieapp.services.utils.APICallback;
import com.sorne.movieapp.services.repositories.MovieRepository;
import com.sorne.movieapp.services.utils.MovieQueryHelper;
import com.sorne.movieapp.services.utils.MovieQueryPair;

import java.util.HashMap;
import java.util.Map;

public class HomeViewModel extends ViewModel {
    private final MovieQueryHelper queryService;
    private final MovieRepository movieRepository;

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository, MovieQueryHelper queryService) {
        this.movieRepository = movieRepository;
        this.queryService = queryService;
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

    public void getDiscoverMovies(APICallback<MovieListResponse> callback, MovieQueryPair... queryPairs){
        Map<String, String> queryOptions = new HashMap<>();

        for (MovieQueryPair queryPair : queryPairs){
            queryOptions.put(queryService.getMovieQueryString(queryPair.getQuery()), queryPair.getValue());
        }

        movieRepository.getDiscoverMovies(queryOptions, callback);
    }

    public void getMovieGenres(APICallback<GenreListResponse> callback){
        movieRepository.getAllMovieGenres(callback);
    }
}