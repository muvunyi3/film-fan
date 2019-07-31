package com.iwax.film;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.Toast;

import com.iwax.film.databinding.ActivityMainBinding;
import com.iwax.film.models.Movie;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    private static final String TAG = "MainActivity";

    //data binding
    ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init(){
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_main));
        transaction.commit();
    }

    @Override
    public void inflateViewMovieFragment(Movie movie) {
        ViewMovieFragment fragment = new ViewMovieFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.intent_movie), movie);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment, getString(R.string.fragment_view_movie));
        transaction.addToBackStack(getString(R.string.fragment_view_movie));
        transaction.commit();
    }

    @Override
    public void sendMovieRating(Movie movie){
        Log.d(TAG, "###### => call sendMovieRating...");
        RatingBar mBar = (RatingBar) findViewById(R.id.movie_rating);
        Toast.makeText(this, "Thanks for rating the : " +movie.getTitle() +" :"+ mBar.getRating(), Toast.LENGTH_SHORT).show();
    }


}
