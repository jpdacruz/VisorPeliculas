package com.jpdacruz.visorpeliculas.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.jpdacruz.visorpeliculas.data.local.MovieEntity;
import com.jpdacruz.visorpeliculas.data.network.Resource;
import com.jpdacruz.visorpeliculas.data.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private final LiveData<Resource<List<MovieEntity>>> popularMovies;

    public MovieViewModel(){

        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){

        return popularMovies;
    }
}
