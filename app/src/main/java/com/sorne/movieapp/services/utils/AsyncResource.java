package com.sorne.movieapp.services.utils;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;

public class AsyncResource<T> {
    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;

    public AsyncResource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> AsyncResource<T> success(@NonNull T data){
        return new AsyncResource<>(Status.SUCCESS, data, null);
    }

    public static <T> AsyncResource<T> error(String message, @Nullable T data){
        return new AsyncResource<>(Status.ERROR, data, message);
    }

    public static <T> AsyncResource<T> loading(@NonNull T data){
        return new AsyncResource<>(Status.LOADING, data, null);
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING,
    }
}
