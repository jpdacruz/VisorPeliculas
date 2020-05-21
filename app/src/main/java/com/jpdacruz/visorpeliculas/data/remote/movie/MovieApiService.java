package com.jpdacruz.visorpeliculas.data.remote.movie;

import com.jpdacruz.visorpeliculas.data.Constantes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    /**
     * Declaracion de la interface con los metodos de accesos a la api
     *
     * metodo GET para buscar movie/popular /upcoming
     * devolver un Call de movieResponse
     */

    @GET(Constantes.API_POPULAR)
    Call<MoviesResponse> loadPopularMoviews();

    @GET(Constantes.API_UPCOMING)
    Call<MoviesResponse> loadUpcomingMoviews();

}
