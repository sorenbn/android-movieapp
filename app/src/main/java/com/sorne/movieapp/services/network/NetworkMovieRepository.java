package com.sorne.movieapp.services.network;

import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.repositories.MovieRepository;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkMovieRepository implements MovieRepository {

    private final MovieAPI api;
    private final String apiKey;

    @Inject
    public NetworkMovieRepository(MovieAPI api, @Named("movie_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public void getMovieDetails(int id, APICallback<Movie> responseCallback) {
        api.getMovieDetails(id, apiKey)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        if(response.isSuccessful()){
                            responseCallback.onResponse(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        responseCallback.onError("Error");
                    }
                });
    }

    @Override
    public void getPopularMovies(APICallback<MovieListResponse> responseCallback) {
        api.getPopularMovies(apiKey)
                .enqueue(new Callback<MovieListResponse>() {
                    @Override
                    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                        if (response.isSuccessful()) {
                            responseCallback.onResponse(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListResponse> call, Throwable t) {
                        responseCallback.onError("Error");
                    }
                });
    }

    @Override
    public void getTopRatedMovies(APICallback<MovieListResponse> responseCallback) {
        api.getTopRatedMovies(apiKey)
                .enqueue(new Callback<MovieListResponse>() {
                    @Override
                    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                        if (response.isSuccessful()) {
                            responseCallback.onResponse(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListResponse> call, Throwable t) {
                        responseCallback.onError("Error");
                    }
                });
    }
}
