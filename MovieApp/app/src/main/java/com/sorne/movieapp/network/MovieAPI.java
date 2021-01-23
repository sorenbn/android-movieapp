package com.sorne.movieapp.network;

import com.sorne.movieapp.models.Movie;
import com.sorne.movieapp.models.MovieListResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {
    @GET("movie/{id}")
    Single<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Single<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey);
}
