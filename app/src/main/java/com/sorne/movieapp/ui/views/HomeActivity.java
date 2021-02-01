package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityHomeBinding;
import com.sorne.movieapp.enums.MovieListType;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.network.APICallback;
import com.sorne.movieapp.ui.adaptors.MovieListAdaptor;
import com.sorne.movieapp.ui.utils.ViewUtils;
import com.sorne.movieapp.viewmodels.HomeViewModel;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    @Inject
    @Named("movie_image_base_url")
    public String baseImageUrl;

    private HomeViewModel viewModel;
    private ActivityHomeBinding dataBinding;

    //TODO: Hardcoded. Make dynamic
    private MovieListAdaptor popularMovieListAdaptor = new MovieListAdaptor(new ArrayList<>());
    private MovieListAdaptor topRatedMovieListAdaptor = new MovieListAdaptor(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBindings();
        fetchMovies();
    }

    private void setupBindings() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        //TODO: Hardcoded. Make dynamic
        dataBinding.homePopularMovies.recyclerMovieList.setAdapter(popularMovieListAdaptor);
        dataBinding.homeTopRatedMovies.recyclerMovieList.setAdapter(topRatedMovieListAdaptor);

        dataBinding.homePopularMovies.homeCategoryTitle.setText("Popular Movies");
        dataBinding.homeTopRatedMovies.homeCategoryTitle.setText("Top-rated Movies");
    }

    private void fetchMovies() {
        fetchFeatureMovie(550);
        fetchMovieLists(MovieListType.Popular, dataBinding.homePopularMovies.recyclerMovieList, popularMovieListAdaptor);
        fetchMovieLists(MovieListType.TopRated, dataBinding.homeTopRatedMovies.recyclerMovieList, topRatedMovieListAdaptor);
    }

    private void fetchFeatureMovie(int movieId) {
        viewModel.getMovieDetails(movieId, new APICallback<Movie>() {
            @Override
            public void onResponse(Movie response) {
                dataBinding.homeFeatureMovie.homeFeatureMovieTitle.setText(response.getTitle());
                String url = baseImageUrl + response.getPosterUrl();
                ViewUtils.loadImage(dataBinding.homeFeatureMovie.homeFeatureMovieImg, url);
            }

            @Override
            public void onError(String errorMessage) {
                dataBinding.homeFeatureMovie.homeFeatureMovieTitle.setText("Unknown");
            }
        });
    }

    private void fetchMovieLists(MovieListType type, RecyclerView recyclerView, MovieListAdaptor adaptor) {
        recyclerView.setVisibility(View.GONE);
        viewModel.getMovieList(type, new APICallback<MovieListResponse>() {
            @Override
            public void onResponse(MovieListResponse response) {
                adaptor.updateData(response.getMovies());
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(String errorMessage) {
                Log.d("MOVIE", "onError: " + errorMessage);
            }
        });
    }
}