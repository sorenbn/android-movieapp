package com.sorne.movieapp.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("localId")
    private String userId;
    @SerializedName("idToken")
    private String idToken;
    @SerializedName("email")
    private String email;

    public User(String userId, String idToken, String email) {
        this.userId = userId;
        this.idToken = idToken;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
