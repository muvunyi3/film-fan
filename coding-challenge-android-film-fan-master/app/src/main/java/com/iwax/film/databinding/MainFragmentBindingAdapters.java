package com.iwax.film.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iwax.film.adapters.MovieAdapter;
import com.iwax.film.models.Movie;

import java.util.List;

public class MainFragmentBindingAdapters {
    private static final int NUM_COLUMNS = 2;

    @BindingAdapter("moviesList")
    public static void setMoviesList(RecyclerView view, List<Movie> movies){
        if(movies == null){
            return;
        }
        RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
        if(layoutManager == null){
            view.setLayoutManager(new GridLayoutManager(view.getContext(), NUM_COLUMNS));
        }
        MovieAdapter adapter = (MovieAdapter) view.getAdapter();
        if(adapter == null){
            adapter = new MovieAdapter(view.getContext(),movies);
            view.setAdapter(adapter);
        }
    }
}
