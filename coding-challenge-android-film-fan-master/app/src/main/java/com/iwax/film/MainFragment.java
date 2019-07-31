package com.iwax.film;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.iwax.film.adapters.MovieAdapter;
import com.iwax.film.databinding.FragmentMainBinding;
import com.iwax.film.models.Movie;
import com.iwax.film.models.MovieModel;
import com.iwax.film.models.MoviesResponse;
import com.iwax.film.services.MovieService;
import com.iwax.film.services.ServiceBuilder;
import com.iwax.film.util.Movies;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

   private static final String TAG = "MainFragment";

    private static final String LANGUAGE = "en-US";

    String key ="a78568486f0eccb1c37aeb90a68ec361";

   //data binding
    FragmentMainBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentMainBinding.inflate(inflater);
        mBinding.swipeRefreshLayout.setOnRefreshListener(this);

        setupMoviesList();

        return mBinding.getRoot();
    }

    private void setupMoviesList(){
        //Movies movies = new Movies();
        List<Movie> movieList = new ArrayList<>();
        //movieList.addAll(Arrays.asList(movies.MOVIES));

        MovieService taskService = ServiceBuilder.buildService(MovieService.class);
        Call<MoviesResponse> call = taskService.getMovies(key, LANGUAGE, 1);

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> request, Response<MoviesResponse> response) {
                Log.d(TAG,"################=> " + response.body().getTotalPages());

                if(response.isSuccessful()){
                    Collections.sort(response.body().getMovies(), MovieModel.movieTitleComparator);
                    for (MovieModel film : response.body().getMovies()){
                        if(film.getPoster_path() != null){
                            Movie movie = new Movie();
                            movie.setId(film.getId());
                            movie.setTitle(film.getTitle());
                            movie.setPopularity(BigDecimal.valueOf(film.getPopularity()));
                            movie.setImage(R.drawable.blue_mug);
                            movie.setPoster("http://image.tmdb.org/t/p/w185/" + film.getPoster_path());
                            movie.setRelease_date(film.getRelease_date());
                            movie.setRating(BigDecimal.valueOf(film.getPopularity()));
                            movie.setNum_ratings(film.getVote_count());
                            movie.setDescription(film.getOverview());
                            movieList.add(movie);
                        }

                    }
                    mBinding.setMovies(movieList);
                }else if(response.code() == 401){
                    Toast.makeText(getContext(),"Your session has expired", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Failed to retrieve the movies", Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<MoviesResponse> request, Throwable t) {
                if(t instanceof IOException){
                    Toast.makeText(getContext(),"The connection error occurred", Toast.LENGTH_LONG).show();
                }else{
                    Log.d(TAG,"################=> Failed to retrieve the movies ");
                    Toast.makeText(getContext(),"Failed to retrieve the movies", Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    @Override
    public void onRefresh() {
       // Movies movies = new Movies();
        //List<Movie> movieList = new ArrayList<>();
        //movieList.addAll(Arrays.asList(movies.MOVIES));
        //((MovieAdapter)mBinding.recyclervView.getAdapter()).refreshList(movieList);
        //onItemLoadComplete();
    }

    void onItemLoadComplete(){
        (mBinding.recyclervView.getAdapter()).notifyDataSetChanged();
        mBinding.swipeRefreshLayout.setRefreshing(false);
    }
}
