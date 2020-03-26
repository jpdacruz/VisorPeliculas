package com.jpdacruz.visorpeliculas.ui.fragmentserie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;
import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;
import com.jpdacruz.visorpeliculas.data.network.Resource;
import com.jpdacruz.visorpeliculas.data.repository.MovieRepository;
import com.jpdacruz.visorpeliculas.data.repository.SerieRepository;

import java.util.List;

public class SerieViewModel extends ViewModel {

    private SerieRepository serieRepository;
    private final LiveData<Resource<List<SerieEntity>>> popularSeries;

    public SerieViewModel(){

        serieRepository = new SerieRepository();
        popularSeries = serieRepository.getPopularSeries();
    }

    public LiveData<Resource<List<SerieEntity>>> getPopularSeries(){

        return popularSeries;
    }
}
