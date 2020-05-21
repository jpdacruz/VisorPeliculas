package com.jpdacruz.visorpeliculas.ui.fragmentomovies;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jpdacruz.visorpeliculas.R;
import com.jpdacruz.visorpeliculas.data.Constantes;
import com.jpdacruz.visorpeliculas.data.local.movie.MovieEntity;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private static final String TAG = "MovieAdapter";
    /**
     * definir List viewmodel
     * definir context para libreria GLIDE
     * ELIMINE LISTENER , comunicacion por viewmodel
     */
    private List<MovieEntity> mValues;
    private Context ctx;

    public MovieAdapter(Context context, List<MovieEntity> items) {
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

        /**
         * indicar contexto
         * cargar imagen URi calidad 500w
         * en image view
         * seteo en manifest userpermission -> internet
         */
        Glide.with(ctx)
                .load(Constantes.API_IMAGES_URL + holder.mItem.getPosterPath())
                .into(holder.imageView);
    }

    /**
     * metodo asignar al list los valores de listadoPeliculas del fragment
     * @param movies
     */
    public void setData(List<MovieEntity> movies){

        this.mValues = movies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        /**
         * evitar recibir mValues = Null
         */
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
