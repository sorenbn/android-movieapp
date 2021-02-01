package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.network.APICallback;

public interface MovieRepository {
    void getMovieDetails(int id, APICallback<Movie> responseCallback);
    void getPopularMovies(APICallback<MovieListResponse> responseCallback);
    void getTopRatedMovies(APICallback<MovieListResponse> responseCallback);
}
