package com.sorne.movieapp.services.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

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

    private MutableLiveData<MovieListResponse> popularMovieResponse = new MutableLiveData<>();
    private MutableLiveData<Movie> movieDetailResponse = new MutableLiveData<>();

    @Inject
    public NetworkMovieRepository(MovieAPI api, @Named("movie_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public LiveData<Movie> getMovieDetails(int id) {
        api.getMovieDetails(id, apiKey)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        movieDetailResponse.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        movieDetailResponse.setValue(null);
                    }
                });

        return movieDetailResponse;
    }

    @Override
    public LiveData<MovieListResponse> getPopularMovies() {
        api.getPopularMovies(apiKey)
                .enqueue(new Callback<MovieListResponse>() {
                    @Override
                    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                        if (response.isSuccessful()) {
                            popularMovieResponse.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListResponse> call, Throwable t) {
                        popularMovieResponse.setValue(null);
                    }
                });

        return popularMovieResponse;
    }

}
