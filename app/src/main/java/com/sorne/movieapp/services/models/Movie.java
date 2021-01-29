package com.sorne.movieapp.services.models;

import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterUrl;
    @SerializedName("backdrop_path")
    private String backdropUrl;
    @SerializedName("vote_average")
    private float rating;

    public Movie(String id, String title, String posterUrl, String backdropUrl, float rating) {
        this.id = id;
        this.title = title;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public float getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdropUrl='" + backdropUrl + '\'' +
                ", rating=" + rating +
                '}';
    }
}
