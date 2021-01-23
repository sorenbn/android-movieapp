package com.sorne.movieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sorne.movieapp.models.Movie;
import com.sorne.movieapp.models.MovieListResponse;
import com.sorne.movieapp.network.NetworkMovieRepository;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public NetworkMovieRepository movieRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: Remove later. Just for testing
        disposable.add(movieRepository.getMovieDetails(550)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Movie>() {
                    @Override
                    public void onSuccess(@NonNull Movie movie) {
                        Log.d("MAIN", "onSuccess: " + movie.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("MAIN", "onError: " + e.toString(), e);
                    }
                }));

        disposable.add(movieRepository.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<MovieListResponse>() {
                    @Override
                    public void onSuccess(@NonNull MovieListResponse movieListResponse) {
                        for (int i = 0; i < movieListResponse.getMovies().size(); i++) {
                            Log.d("MAIN", "onSuccess: " + movieListResponse.getMovies().get(i).toString());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("MAIN", "onError: " + e.toString(), e);
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}