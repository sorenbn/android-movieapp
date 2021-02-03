package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.utils.APICallback;

public interface MovieRepository {
    void getMovieDetails(int id, APICallback<Movie> responseCallback);
    void getPopularMovies(APICallback<MovieListResponse> responseCallback);
    void getTopRatedMovies(APICallback<MovieListResponse> responseCallback);
    void getAllMovieGenres(APICallback<GenreListResponse> responseCallback);
}
