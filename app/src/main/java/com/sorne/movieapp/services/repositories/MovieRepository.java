package com.sorne.movieapp.services.repositories;

import androidx.lifecycle.LiveData;

import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;

public interface MovieRepository {
    LiveData<Movie> getMovieDetails(int id);
    LiveData<MovieListResponse> getPopularMovies();
}
