package com.sorne.movieapp.network;

import com.sorne.movieapp.models.Movie;
import com.sorne.movieapp.models.MovieListResponse;
import com.sorne.movieapp.repositories.MovieRepository;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.rxjava3.core.Single;

public class NetworkMovieRepository implements MovieRepository {

    private final MovieAPI api;
    private final String apiKey;

    @Inject
    public NetworkMovieRepository(MovieAPI api, @Named("movie_api_key")String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public Single<Movie> getMovieDetails(int id) {
        return api.getMovieDetails(id, apiKey);
    }

    @Override
    public Single<MovieListResponse> getPopularMovies() {
        return api.getPopularMovies(apiKey);
    }

}
