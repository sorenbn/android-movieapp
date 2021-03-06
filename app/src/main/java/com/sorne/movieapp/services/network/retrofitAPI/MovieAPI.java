package com.sorne.movieapp.services.network.retrofitAPI;

import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface MovieAPI {
    @GET("movie/{id}")
    Call<Movie> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieListResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MovieListResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MovieListResponse> getDiscoverMovies(@QueryMap Map<String, String> queryOptions, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Call<GenreListResponse> getAllMovieGenres(@Query("api_key") String apiKey);
}
