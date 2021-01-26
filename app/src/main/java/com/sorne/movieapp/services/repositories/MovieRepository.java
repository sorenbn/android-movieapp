package com.sorne.movieapp.services.repositories;

import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;

import io.reactivex.rxjava3.core.Single;

public interface MovieRepository {
    Single<Movie> getMovieDetails(int id);
    Single<MovieListResponse> getPopularMovies();
}
