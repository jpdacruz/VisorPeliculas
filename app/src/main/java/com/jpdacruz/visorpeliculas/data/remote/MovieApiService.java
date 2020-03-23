package com.jpdacruz.visorpeliculas.data.remote;

import com.jpdacruz.visorpeliculas.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMoviews();

}
