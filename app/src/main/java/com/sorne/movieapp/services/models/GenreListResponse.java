package com.sorne.movieapp.services.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GenreListResponse {
    @SerializedName("genres")
    private List<Genre> genres;

    public GenreListResponse(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
