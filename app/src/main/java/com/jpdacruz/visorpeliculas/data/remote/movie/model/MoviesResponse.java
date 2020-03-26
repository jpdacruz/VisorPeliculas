package com.jpdacruz.visorpeliculas.data.remote.movie.model;

import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;

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
