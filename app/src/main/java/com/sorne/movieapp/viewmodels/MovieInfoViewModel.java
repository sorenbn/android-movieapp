package com.sorne.movieapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.sorne.movieapp.services.models.Movie;

public class MovieInfoViewModel extends ViewModel {
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
