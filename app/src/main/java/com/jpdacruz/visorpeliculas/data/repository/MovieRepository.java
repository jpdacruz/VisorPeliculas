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
import com.jpdacruz.visorpeliculas.data.remote.movie.MoviesResponse;
import com.jpdacruz.visorpeliculas.data.remote.RequestInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieRepository {

    private final MovieApiService movieApiService;
    private final MovieDao movieDao;

    /**
     * constructor
     */
    public MovieRepository() {

        /**
         * crea room database
         * parametros MyApp.Context,
         * asignamos nombre db
         */

        MovieRoomDataBase movieRoomDataBase =
                Room.databaseBuilder(MyApp.getContext(),
                        MovieRoomDataBase.class,
                        Constantes.DB_NAME)
                        .build();
        movieDao = movieRoomDataBase.getMovieDao();

        /**
         * request interceptor
         * incluir en la cabecera el Tokken (api key)
         * a las peticiones que hacemos a la api
         * con esto indicamos que cliente somos
         */
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        okHttpBuilder.addInterceptor(new RequestInterceptor());
        OkHttpClient cliente = okHttpBuilder.build();

        /**
         * conexion remota retrofit
         */
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.API_BASE_URL)
                //agregamos el cliente
                .client(cliente)
                //conversor Json->Gson
                .addConverterFactory(GsonConverterFactory.create()).build();

        movieApiService = retrofit.create(MovieApiService.class);
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){

        /**
         * obtener datos de la api
         */

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
