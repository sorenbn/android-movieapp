package com.sorne.movieapp.viewmodels;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.repositories.MovieRepository;

public class HomeViewModel extends ViewModel {

    private final MovieRepository movieRepository;

    private MutableLiveData<MovieListResponse> popularMovieResponse = new MutableLiveData<>();

    @ViewModelInject
    public HomeViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<MovieListResponse> popularMoviesLiveData(){
        return Transformations.switchMap(popularMovieResponse, input -> {
            return movieRepository.getPopularMovies();
        });
    }

    public void fetchPopularMovies(){
        Log.d("FETCH", "fetching movies..");
        popularMovieResponse.setValue(null);
    }
}
