package com.jpdacruz.visorpeliculas.ui.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpdacruz.visorpeliculas.R;
import com.jpdacruz.visorpeliculas.data.local.MovieEntity;
import com.jpdacruz.visorpeliculas.data.remote.model.Constantes;


import java.util.List;

public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    private List<MovieEntity> mValues;
    private Context ctx;

    public MyMovieRecyclerViewAdapter(Context context,List<MovieEntity> items) {
        mValues = items;
        ctx = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        Glide.with(ctx)
                .load(Constantes.API_IMAGES_URL + holder.mItem.getPosterPath())
                .into(holder.imageView);
    }

    public void setData(List<MovieEntity> movies){

        this.mValues = movies;
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
        public ImageView imageView;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imageView = view.findViewById(R.id.imageViewCover);
        }
    }
}