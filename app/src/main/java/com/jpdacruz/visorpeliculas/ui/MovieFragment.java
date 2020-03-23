package com.jpdacruz.visorpeliculas.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jpdacruz.visorpeliculas.R;
import com.jpdacruz.visorpeliculas.data.local.MovieEntity;
import com.jpdacruz.visorpeliculas.data.network.Resource;
import com.jpdacruz.visorpeliculas.ui.adapter.MyMovieRecyclerViewAdapter;
import com.jpdacruz.visorpeliculas.ui.viewmodel.MovieViewModel;

import java.util.List;

public class MovieFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private MyMovieRecyclerViewAdapter adapter;
    private List<MovieEntity> listadoPeliculas;
    private MovieViewModel movieViewModel;

    public MovieFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MovieFragment newInstance(int columnCount) {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }

        movieViewModel = ViewModelProviders.of(getActivity()).get(MovieViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            adapter = new MyMovieRecyclerViewAdapter(getActivity(), listadoPeliculas);
            recyclerView.setAdapter(adapter);
            loadMovies();
        }
        return view;
    }

    private void loadMovies() {

        movieViewModel.getPopularMovies().observe(getActivity(), new Observer<Resource<List<MovieEntity>>>() {
            @Override
            public void onChanged(Resource<List<MovieEntity>> listResource) {

                listadoPeliculas = listResource.data;
                adapter.setData(listadoPeliculas);
            }
        });
    }
}
