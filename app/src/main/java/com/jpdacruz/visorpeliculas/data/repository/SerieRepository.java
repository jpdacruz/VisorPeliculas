package com.jpdacruz.visorpeliculas.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.data.local.MovieRoomDataBase;
import com.jpdacruz.visorpeliculas.data.local.series.SerieDao;
import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;
import com.jpdacruz.visorpeliculas.data.network.NetworkBoundResource;
import com.jpdacruz.visorpeliculas.data.network.Resource;
import com.jpdacruz.visorpeliculas.data.remote.RequestInterceptor;
import com.jpdacruz.visorpeliculas.data.remote.serie.SerieApiService;
import com.jpdacruz.visorpeliculas.data.remote.serie.SeriesResponse;
import com.jpdacruz.visorpeliculas.myapp.MyApp;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SerieRepository {

    private final SerieApiService serieApiService;
    private final SerieDao serieDao;

    public SerieRepository() {

        //instancias el MovieDao de db local en el repositorio

        MovieRoomDataBase movieRoomDataBase =
                Room.databaseBuilder(MyApp.getContext(),MovieRoomDataBase.class, Constantes.DB_NAME).build();
        serieDao = movieRoomDataBase.getSerieDao();

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

        serieApiService = retrofit.create(SerieApiService.class);
    }

    public LiveData<Resource<List<SerieEntity>>> getPopularSeries(){

        //movientiry de la base de datos local y movieResponse de la base de datos de la api
        //con este metodo se decide de donde se buscan los datos
        return new NetworkBoundResource<List<SerieEntity>, SeriesResponse>(){

            @Override
            protected void saveCallResult(@NonNull SeriesResponse item) {
                serieDao.saveSeries(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<SerieEntity>> loadFromDb() {
                return serieDao.loadPopularSeriesDao();
            }

            @NonNull
            @Override
            protected Call<SeriesResponse> createCall() {
                return serieApiService.loadPopularSeriesRemote();
            }
        }.getAsLiveData();
    }
}
