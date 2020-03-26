package com.jpdacruz.visorpeliculas.data.remote.movie;

import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.data.remote.movie.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET(Constantes.API_POPULAR)
    Call<MoviesResponse> loadPopularMoviews();

    @GET("movie/upcoming")
    Call<MoviesResponse> loadUpcomingMoviews();

}
