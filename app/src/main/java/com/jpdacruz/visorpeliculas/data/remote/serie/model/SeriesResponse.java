package com.jpdacruz.visorpeliculas.data.remote.serie.model;

import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;

import java.util.List;

public class SeriesResponse {

    private List<SerieEntity> results;

    public List<SerieEntity> getResults() {
        return results;
    }

    public void setResults(List<SerieEntity> results) {
        this.results = results;
    }
}
