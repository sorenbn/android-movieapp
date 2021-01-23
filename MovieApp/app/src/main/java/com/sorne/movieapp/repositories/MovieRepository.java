package com.sorne.movieapp.repositories;

import com.sorne.movieapp.models.Movie;
import com.sorne.movieapp.models.MovieListResponse;

import io.reactivex.rxjava3.core.Single;

public interface MovieRepository {
    Single<Movie> getMovieDetails(int id);
    Single<MovieListResponse> getPopularMovies();
}
