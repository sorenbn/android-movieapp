package com.sorne.movieapp.services.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String description;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("backdrop_path")
    private String backdropUrl;

    @SerializedName("vote_average")
    private float rating;

    @SerializedName("vote_count")
    private int voteCount;

    public Movie(String id, String title, String description, String posterUrl, String backdropUrl, float rating, int voteCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
        this.rating = rating;
        this.voteCount = voteCount;
    }

    protected Movie(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        posterUrl = in.readString();
        backdropUrl = in.readString();
        rating = in.readFloat();
        voteCount = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(posterUrl);
        dest.writeString(backdropUrl);
        dest.writeFloat(rating);
        dest.writeInt(voteCount);
    }
}
