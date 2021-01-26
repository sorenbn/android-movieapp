package com.sorne.movieapp.services.network;

import android.util.Log;

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

    @Inject
    public NetworkMovieRepository(MovieAPI api, @Named("movie_api_key") String apiKey) {
        this.api = api;
        this.apiKey = apiKey;
    }

    @Override
    public LiveData<Movie> getMovieDetails(int id) {
        MutableLiveData<Movie> movieResponse = new MutableLiveData<>();

        api.getMovieDetails(id, apiKey)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(Call<Movie> call, Response<Movie> response) {
                        movieResponse.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Movie> call, Throwable t) {
                        movieResponse.setValue(null);
                    }
                });

        return movieResponse;
    }

    @Override
    public LiveData<MovieListResponse> getPopularMovies() {
        //TODO: SOMETHING IN HERE IS MESSING UP AND NOT SENDING BACK CHANGE IN DATA!
        MutableLiveData<MovieListResponse> movieResponse = new MutableLiveData<>();

        api.getPopularMovies(apiKey)
                .enqueue(new Callback<MovieListResponse>() {
                    @Override
                    public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("HOME", "setting value from REST API");
                            movieResponse.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieListResponse> call, Throwable t) {
                        movieResponse.setValue(null);
                    }
                });

        return movieResponse;
    }

}
