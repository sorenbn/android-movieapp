package com.sorne.movieapp.ui.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ActivityHomeBinding;
import com.sorne.movieapp.enums.MovieQuery;
import com.sorne.movieapp.enums.MovieListType;
import com.sorne.movieapp.services.models.Genre;
import com.sorne.movieapp.services.models.GenreListResponse;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.services.models.MovieListResponse;
import com.sorne.movieapp.services.utils.APICallback;
import com.sorne.movieapp.services.utils.MovieQueryPair;
import com.sorne.movieapp.ui.adaptors.MovieListAdaptor;
import com.sorne.movieapp.ui.utils.ViewUtils;
import com.sorne.movieapp.viewmodels.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupBindings();
        fetchMovies();
    }

    private void setupBindings() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
    }

    private void fetchMovies() {
        fetchFeatureMovie(550);

        viewModel.getMovieGenres(new APICallback<GenreListResponse>() {
            @Override
            public void onResponse(GenreListResponse response) {

                for (Genre genre : response.getGenres()) {
                    viewModel.getDiscoverMovies(new APICallback<MovieListResponse>() {
                        @Override
                        public void onResponse(MovieListResponse response) {
                            createCategoryList(genre.getName(), response.getMovies());
                        }

                        @Override
                        public void onError(String errorMessage) {

                        }
                    }, new MovieQueryPair(MovieQuery.Genre, String.valueOf(genre.getId())));
                }
            }

            @Override
            public void onError(String errorMessage) {

            }
        });
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

    //TODO: Make this work with data-binding
    private void createCategoryList(String categoryTitle, List<Movie> movies) {
        LinearLayout layout = dataBinding.homeCategoryListDynamic;
        View listLayout = getLayoutInflater().inflate(R.layout.home_movie_category_list, layout, false);
        layout.addView(listLayout);

        RecyclerView recycler = listLayout.findViewById(R.id.recyclerMovieList);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        MovieListAdaptor adaptor = new MovieListAdaptor(new ArrayList<>());

        recycler.setAdapter(adaptor);
        adaptor.updateData(movies);

        TextView title = listLayout.findViewById(R.id.home_CategoryTitle);
        title.setText(categoryTitle);
    }
}