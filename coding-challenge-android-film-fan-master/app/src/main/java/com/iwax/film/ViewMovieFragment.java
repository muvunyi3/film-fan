package com.iwax.film;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iwax.film.databinding.FragmentViewMovieBinding;
import com.iwax.film.models.Movie;


public class ViewMovieFragment extends Fragment {

    private static final String TAG = "ViewMovieFragment";

    private Movie mMovie;

    FragmentViewMovieBinding mBinding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();

        if(bundle != null){
            mMovie = bundle.getParcelable("intent_movie");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentViewMovieBinding.inflate(inflater);
        mBinding.setIMainActivity((IMainActivity)getActivity());

        MovieViewModel movieView = new MovieViewModel();
        movieView.setMovie(mMovie);
        mBinding.setMovieView(movieView);

        return mBinding.getRoot();
    }

}
