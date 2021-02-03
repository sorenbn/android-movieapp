package com.sorne.movieapp.services.utils;

import com.sorne.movieapp.enums.DiscoverQuery;

public class DiscoverQueryPair {
    private DiscoverQuery query;
    private String value;

    public DiscoverQueryPair(DiscoverQuery query, String value) {
        this.query = query;
        this.value = value;
    }

    public DiscoverQuery getQuery() {
        return query;
    }

    public String getValue() {
        return value;
    }
}
