package com.jpdacruz.visorpeliculas.ui.fragmentserie;

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
import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;
import com.jpdacruz.visorpeliculas.data.network.Resource;

import java.util.List;


public class SerieFragment extends Fragment {

    private int mColumnCount = 2;
    private SerieAdapter adapter;
    private List<SerieEntity> listadoSeries;
    private SerieViewModel serieViewModel;
    public SerieFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        serieViewModel = ViewModelProviders.of(getActivity()).get(SerieViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapter = new SerieAdapter(getActivity(),listadoSeries);
            recyclerView.setAdapter(adapter);
            loadSeries();
        }
        return view;
    }

    private void loadSeries() {

        serieViewModel.getPopularSeries().observe(getActivity(), new Observer<Resource<List<SerieEntity>>>() {
            @Override
            public void onChanged(Resource<List<SerieEntity>> listResource) {

                listadoSeries = listResource.data;
                adapter.setData(listadoSeries);
            }
        });
    }
}
