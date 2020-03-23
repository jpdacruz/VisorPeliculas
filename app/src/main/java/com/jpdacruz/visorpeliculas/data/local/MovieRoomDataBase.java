package com.jpdacruz.visorpeliculas.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class},version = 1,exportSchema = false)
public abstract class MovieRoomDataBase extends RoomDatabase {

    public abstract MovieDao getMovieDao();
}
