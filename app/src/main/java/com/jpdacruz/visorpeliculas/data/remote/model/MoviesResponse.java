package com.jpdacruz.visorpeliculas.data.remote.model;

import com.jpdacruz.visorpeliculas.data.local.MovieEntity;

import java.util.List;

public class MoviesResponse {

    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

}