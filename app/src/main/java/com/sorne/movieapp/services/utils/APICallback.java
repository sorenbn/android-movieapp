package com.sorne.movieapp.services.utils;

public interface APICallback<T> {
    void onResponse(T response);
    void onError(String errorMessage);
}
