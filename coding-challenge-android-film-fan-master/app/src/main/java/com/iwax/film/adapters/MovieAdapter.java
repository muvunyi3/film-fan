package com.iwax.film.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iwax.film.IMainActivity;
import com.iwax.film.R;
import com.iwax.film.databinding.MovieItemBinding;
import com.iwax.film.models.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends  RecyclerView.Adapter<MovieAdapter.BindingHolder> {
    private static final String TAG = "MovieAdapter";

    private List<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MovieAdapter(Context context, List<Movie> movies) {
        mMovies = movies;
        mContext = context;
    }

    public void refreshList(List<Movie> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       //ViewDataBinding binding = DataBindingUtil.inflate(
       //         LayoutInflater.from(mContext), R.layout.movie_item, parent, false);
        MovieItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(mContext), R.layout.movie_item, parent, false);

        return new BindingHolder(binding.getRoot());
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, final int position) {
        Movie movie = mMovies.get(position);

        holder.binding.setIMainActivity((IMainActivity) mContext);
        //holder.binding.setVariable(BR.movie, movie);
        //holder.binding.executePendingBindings();
        holder.binding.setMovie(movie);
        //holder.binding.setTestUrl("");
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class BindingHolder extends RecyclerView.ViewHolder{

        //ViewDataBinding binding;
        MovieItemBinding binding;

        public BindingHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
