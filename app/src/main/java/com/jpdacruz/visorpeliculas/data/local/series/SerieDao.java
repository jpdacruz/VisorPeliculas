package com.jpdacruz.visorpeliculas.data.local.series;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SerieDao {

    @Query("SELECT * FROM series")
    LiveData<List<SerieEntity>> loadPopularSeriesDao();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveSeries(List<SerieEntity> serieEntityList);
}
