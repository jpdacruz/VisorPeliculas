package com.jpdacruz.visorpeliculas.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.myapp.MyApp;
import com.jpdacruz.visorpeliculas.data.local.movie.MovieDao;
import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;
import com.jpdacruz.visorpeliculas.data.local.MovieRoomDataBase;
import com.jpdacruz.visorpeliculas.data.network.NetworkBoundResource;
import com.jpdacruz.visorpeliculas.data.network.Resource;
import com.jpdacruz.visorpeliculas.data.remote.movie.MovieApiService;
import com.jpdacruz.visorpeliculas.data.remote.movie.model.MoviesResponse;
import com.jpdacruz.visorpeliculas.data.remote.RequestInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    public MovieRepository() {

        //instancias el MovieDao de db local en el repositorio

        MovieRoomDataBase movieRoomDataBase =
                Room.databaseBuilder(MyApp.getContext(),MovieRoomDataBase.class,Constantes.DB_NAME).build();
        movieDao = movieRoomDataBase.getMovieDao();
        //incluir cabecera api por interceptor
        //peticion tokken

        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttpBuilder.build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_BASE_URL)
                //agregamos el cliente que obtenermos en Okhtttp al cual le pusimos el interceptor
                .client(cliente)
                .addConverterFactory(GsonConverterFactory.create()).build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){

        //movientiry de la base de datos local y movieResponse de la base de datos de la api
        //con este metodo se decide de donde se buscan los datos
        return new NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                movieDao.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<MovieEntity>> loadFromDb() {
                return movieDao.loadPopularMovies();
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return movieApiService.loadUpcomingMoviews();
            }
        }.getAsLiveData();
    }


}
