package com.sorne.movieapp.adaptors;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sorne.movieapp.R;
import com.sorne.movieapp.databinding.ItemMovieListThumbnailBinding;
import com.sorne.movieapp.models.Movie;

import java.util.List;

public class MovieListAdaptor extends RecyclerView.Adapter<MovieListAdaptor.MovieListViewHolder> {

    private ItemMovieListThumbnailBinding viewBinding;
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
        viewBinding = ItemMovieListThumbnailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
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

        private ItemMovieListThumbnailBinding viewBinding;

        public MovieListViewHolder(@NonNull ItemMovieListThumbnailBinding binding) {
            super(binding.getRoot());
            this.viewBinding = binding;
        }

        void updateViewData(Movie movie){
            //TODO: Fix image base URL injection
            viewBinding.movieListThumbnailTitle.setText(movie.getTitle());
            String url = viewBinding.movieListThumbnailImg.getContext().getString(R.string.movie_api_base_url_poster) + movie.getPosterUrl();
            Glide.with(viewBinding.movieListThumbnailImg.getContext())
                    .load(url)
                    .into(viewBinding.movieListThumbnailImg);
        }
    }
}
