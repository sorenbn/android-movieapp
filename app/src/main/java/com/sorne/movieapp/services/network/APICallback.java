package com.sorne.movieapp.services.network;

public interface APICallback<T> {
    void onResponse(T response);
    void onError(String errorMessage);
}
