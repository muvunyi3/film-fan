package com.iwax.film;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.iwax.film.models.Movie;

public class MovieViewModel extends BaseObservable {

    private Movie movie;

    //private int quantity;

    private boolean imageVisibility = false;

    @Bindable
    public boolean isImageVisibility() {
        return imageVisibility;
    }

    @Bindable
    public Movie getMovie() {
        return movie;
    }


    public void setMovie(Movie movie) {
        this.movie = movie;
        notifyPropertyChanged(BR.movie);
    }

    public void setImageVisibility(boolean imageVisibility) {
        this.imageVisibility = imageVisibility;
        notifyPropertyChanged(BR.imageVisibility);
    }

    public RequestListener getCustomRequestListener(){

        return new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                 setImageVisibility(true);
                return false;
            }
        };

    }
}
