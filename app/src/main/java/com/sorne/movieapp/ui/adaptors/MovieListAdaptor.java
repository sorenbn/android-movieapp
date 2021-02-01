package com.sorne.movieapp.ui.adaptors;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.HomeMovieThumbnailBinding;
import com.sorne.movieapp.services.models.Movie;
import com.sorne.movieapp.ui.utils.ViewUtils;

import java.util.List;

public class MovieListAdaptor extends RecyclerView.Adapter<MovieListAdaptor.MovieListViewHolder> {

    private HomeMovieThumbnailBinding viewBinding;
    private List<Movie> movies;

    public MovieListAdaptor(List<Movie> movies) {
        this.movies = movies;
    }

    public void updateData(List<Movie> movies){
        this.movies.clear();
        this.movies.addAll(movies);

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewBinding = HomeMovieThumbnailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MovieListViewHolder(viewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListViewHolder holder, int position) {
        holder.updateViewData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder{

        private HomeMovieThumbnailBinding viewBinding;

        public MovieListViewHolder(@NonNull HomeMovieThumbnailBinding binding) {
            super(binding.getRoot());
            this.viewBinding = binding;
        }

        void updateViewData(Movie movie){
            //TODO: Fix image base URL injection
            String url = viewBinding.movieListThumbnailImg.getContext().getString(R.string.movie_api_base_url_images) + movie.getPosterUrl();
            ViewUtils.loadImage(viewBinding.movieListThumbnailImg, url);
        }
    }
}
