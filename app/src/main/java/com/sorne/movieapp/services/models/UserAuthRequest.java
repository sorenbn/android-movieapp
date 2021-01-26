package com.sorne.movieapp.services.models;

import com.google.gson.annotations.SerializedName;

public class UserAuthRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public UserAuthRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserAuthRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}