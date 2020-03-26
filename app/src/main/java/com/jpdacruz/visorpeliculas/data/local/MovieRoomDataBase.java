package com.jpdacruz.visorpeliculas.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jpdacruz.visorpeliculas.data.local.movie.MovieDao;
import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;
import com.jpdacruz.visorpeliculas.data.local.series.SerieDao;
import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;

@Database(entities = {MovieEntity.class, SerieEntity.class},version = 1,exportSchema = false)
public abstract class MovieRoomDataBase extends RoomDatabase {

    public abstract MovieDao getMovieDao();
    public abstract SerieDao getSerieDao();
}
