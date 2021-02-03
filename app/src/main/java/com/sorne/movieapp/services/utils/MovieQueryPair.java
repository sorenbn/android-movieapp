package com.sorne.movieapp.services.utils;

import com.sorne.movieapp.enums.MovieQuery;

public class MovieQueryPair {
    private MovieQuery query;
    private String value;

    public MovieQueryPair(MovieQuery query, String value) {
        this.query = query;
        this.value = value;
    }

    public MovieQuery getQuery() {
        return query;
    }

    public String getValue() {
        return value;
    }
}
