package com.jpdacruz.visorpeliculas.data.local.movie;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    /**
     * select all
     * metodo que devuelve un list de movieEntity
     */
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadPopularMovies();

    /**
     * metodo insert nueva lista peliculas
     * recibe como parametro un list de peliculas
     * en conflicto reemplaza
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<MovieEntity> movieEntityList);
}
