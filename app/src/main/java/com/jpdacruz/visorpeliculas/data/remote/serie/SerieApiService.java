package com.jpdacruz.visorpeliculas.data.remote.serie;

import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.data.remote.serie.model.SeriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SerieApiService {

    @GET(Constantes.API_SERIES_POPULAR)
    Call<SeriesResponse> loadPopularSeriesRemote();
}
