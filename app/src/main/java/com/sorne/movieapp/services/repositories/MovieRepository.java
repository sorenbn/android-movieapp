package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.utils.APICallback;

import java.util.Map;

public interface MovieRepository {
    void getMovieDetails(int id, APICallback<Movie> responseCallback);
    void getPopularMovies(APICallback<MovieListResponse> responseCallback);
    void getTopRatedMovies(APICallback<MovieListResponse> responseCallback);
    void getDiscoverMovies(Map<String, String> queryOptions, APICallback<MovieListResponse> responseCallback);
    void getAllMovieGenres(APICallback<GenreListResponse> responseCallback);
}
