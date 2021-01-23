package com.sorne.movieapp.network;

import com.sorne.movieapp.models.Movie;
import com.sorne.movieapp.models.MovieListResponse;
import com.sorne.movieapp.repositories.MovieRepository;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkMovieRepository implements MovieRepository {

    //TODO: Setup DI
    //TODO: Get base url from strings.xml
    private final MovieAPI api = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieAPI.class);

    public NetworkMovieRepository() {
    }

    @Override
    public Single<Movie> getMovieDetails(int id) {
        //TODO: Get api key from string.xml (and later somewhere more hidden)
        return api.getMovieDetails(id, "e68b4b7e5c3ce8d188bf58c360b51b51");
    }

    @Override
    public Single<MovieListResponse> getPopularMovies() {
        //TODO: Get api key from string.xml (and later somewhere more hidden)
        return api.getPopularMovies("e68b4b7e5c3ce8d188bf58c360b51b51");
    }

}
