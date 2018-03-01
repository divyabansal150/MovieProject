package com.example.dbansal.movieretroproject.View;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.Model.MovieProject;
import com.example.dbansal.movieretroproject.Model.Results;
import com.example.dbansal.movieretroproject.R;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;


/**
 * Created by d.bansal on 26/2/18.
 */

public class MovieListFragment extends Fragment implements  MovieListAdapter.IOnItemClickInterface, INetworkCall {

    private String query = null;
    private SearchView searchView;
    private RecyclerView recyclerView;
    private static DetailFragment detailFragment;
    private static CompositeDisposable mCompositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCompositeDisposable = new CompositeDisposable();
    }


    static MovieListAdapter movieListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragmentlayout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        movieListAdapter = new MovieListAdapter(this, new ArrayList<Results>(0), getActivity());
        movieListAdapter.setListener(this);
        recyclerView.setAdapter(movieListAdapter);
        ((InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(container.getWindowToken(), 0);

        makeNetworkRequest();

        return view;
    }

    private void makeNetworkRequest() {
        Bundle b = getArguments();
        if (b != null) {
           query =  b.getString("query");
            if( query == null) {
                if (b.getString("queryType").equals("Top")) {
                    Service.getMovie("top_rated", this, false);
                } else if (b.getString("queryType").equals("Popular")) {
                    Service.getMovie("popular", this, false);
                }
            }else{
                Service.getMovie(query, this, true);
            }
        }else {
            Service.getMovie("now_playing", this, false);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                SearchFragment searchFragment = new SearchFragment();
                getFragmentManager().beginTransaction().replace(R.id.mainContainer,searchFragment).addToBackStack("").commit();
                break;

        }
        return true;
    }


    @Override
    public void onItemClicked(Long movieId) {
        detailFragment = new DetailFragment();
        Bundle b = new Bundle();
        b.putLong("id", movieId);
        detailFragment.setArguments(b);
        getFragmentManager().beginTransaction().
                replace(R.id.mainContainer, detailFragment).addToBackStack("").commit();


    }


    @Override
    public void handleResponse(MovieProject value) {
        movieListAdapter.updateMovies(value.getResults());
    }

    @Override
    public void handleError(Throwable error) {
        Log.e("Response Error", error.getMessage());
    }

    @Override
    public void handleDetailResponse(MovieDetail value) {

    }


}
