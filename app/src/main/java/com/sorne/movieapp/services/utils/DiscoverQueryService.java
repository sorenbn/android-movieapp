package com.sorne.movieapp.services.utils;

import android.util.Log;

import com.sorne.movieapp.enums.DiscoverQuery;

import java.util.HashMap;
import java.util.Map;

public class DiscoverQueryService {
    private Map<DiscoverQuery, String> allQueries;

    public DiscoverQueryService() {
        setupMap();
    }

    private void setupMap() {
        allQueries = new HashMap<>();
        allQueries.put(DiscoverQuery.Genre, "with_genres");
        allQueries.put(DiscoverQuery.Page, "page");
        allQueries.put(DiscoverQuery.ReleaseYear, "primary_release_year");
    }

    public String getDiscoverQuery(DiscoverQuery query) {
        if (allQueries.containsKey(query)) {
            return allQueries.get(query);
        }

        Log.e("QUERY", "ERROR: No query with key " + query.toString() +  " could be found. Did you forget to add it to the list?");
        return null;
    }
}
