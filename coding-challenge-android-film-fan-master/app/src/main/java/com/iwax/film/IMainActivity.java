package com.iwax.film;

import com.iwax.film.models.Movie;

public interface IMainActivity {
    void inflateViewMovieFragment(Movie movie);

    void sendMovieRating(Movie movie);
}
