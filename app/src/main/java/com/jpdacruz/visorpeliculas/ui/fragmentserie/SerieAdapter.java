package com.jpdacruz.visorpeliculas.ui.fragmentserie;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpdacruz.visorpeliculas.R;
import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.data.local.series.SerieEntity;


import java.util.List;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.ViewHolder> {

    private List<SerieEntity> mValues;
    private Context ctx;

    public SerieAdapter(Context context, List<SerieEntity> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_serie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Glide.with(ctx)
                .load(Constantes.API_IMAGES_URL + holder.mItem.getPosterPath())
                .into(holder.imageView);
    }

    public void setData(List<SerieEntity> series){

        this.mValues = series;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mValues!=null)
            return mValues.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public SerieEntity mItem;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.imageViewSeries);
        }
    }
}
