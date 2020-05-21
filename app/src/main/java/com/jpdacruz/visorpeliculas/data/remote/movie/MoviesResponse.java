package com.jpdacruz.visorpeliculas.data.remote.movie;

import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;

import java.util.List;

public class MoviesResponse {

    /**
     * model-> definir clase Movie response
     * generar get y set result
     */
    private List<MovieEntity> results;

    public List<MovieEntity> getResults() {
        return results;
    }

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

}
