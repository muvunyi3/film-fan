package com.iwax.film.services;

import com.iwax.film.models.MoviesResponse;
import com.iwax.film.models.RatingRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface MovieService {

    @GET("/3/discover/movie?sort_by=primary_release_date.desc")
    Call<MoviesResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @POST("/movie/{movie_id}/rating")
    Call<MoviesResponse> postRating(@Body RatingRequest request, @Path("movie_id")int movie_id);
}
