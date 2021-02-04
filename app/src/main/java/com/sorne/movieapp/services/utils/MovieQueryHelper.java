package com.sorne.movieapp.services.utils;

import android.util.Log;

import com.sorne.movieapp.enums.MovieQuery;

import java.util.HashMap;
import java.util.Map;

public class MovieQueryHelper {
    private Map<MovieQuery, String> allQueries;

    public MovieQueryHelper() {
        setupMap();
    }

    private void setupMap() {
        allQueries = new HashMap<>();
        allQueries.put(MovieQuery.GENRE, "with_genres");
        allQueries.put(MovieQuery.PAGE, "page");
        allQueries.put(MovieQuery.RELEASE_YEAR, "primary_release_year");
    }

    public String getMovieQueryString(MovieQuery query) {
        if (allQueries.containsKey(query)) {
            return allQueries.get(query);
        }

        Log.e("QUERY", "ERROR: No query with key " + query.toString() +  " could be found. Did you forget to add it to the list?");
        return null;
    }
}
