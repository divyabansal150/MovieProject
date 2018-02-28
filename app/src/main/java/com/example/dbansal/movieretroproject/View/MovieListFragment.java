package com.example.dbansal.movieretroproject.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbansal.movieretroproject.Network.ApiUtils;
import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.Model.MovieProject;
import com.example.dbansal.movieretroproject.Model.Results;
import com.example.dbansal.movieretroproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d.bansal on 26/2/18.
 */

public class MovieListFragment extends Fragment implements SearchView.OnQueryTextListener,MovieListAdapter.IOnItemClickInterface {

    private static MovieDetail detailResponse;
    private String query = null,sort = null;
    private SearchView searchView;
    ViewPager viewPagerTab;
    static  final int categoryNo = 5;
    DrawerLayout drawerLayout;
    private RecyclerView recyclerView;
    private static DetailFragment detailFragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }


    static MovieListAdapter movieListAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list_fragmentlayout,container,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        movieListAdapter = new MovieListAdapter(this,new ArrayList<Results>(0),getActivity());
        movieListAdapter.setListener(this);
        recyclerView.setAdapter(movieListAdapter);
        searchView = (SearchView)view.findViewById(R.id.searchview);
        Bundle b = getArguments();
        if(b!= null){
            if(b.getString("queryType").equals("Top")){
                getMovie("top_rated");
            }else if(b.getString("queryType").equals("Popular")){
                getMovie("popular");
            }
        }else {
            getMovie("now_playing");
        }

        return view;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.option_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.search:
                if(searchView != null) {
                    searchView.setVisibility(View.VISIBLE);
                    searchView.setOnQueryTextListener(this);
                }
                break;

        }
        return true;
    }

    public static void getMovie(String type) {
            ApiUtils.getService().getMovie(type).enqueue(new Callback<MovieProject>() {
                @Override
                public void onResponse(Call<MovieProject> call, Response<MovieProject> response) {
                    if (response.isSuccessful()) {
                        movieListAdapter.updateMovies(response.body().getResults());
                    } else {
                    }
                }
                @Override
                public void onFailure(Call<MovieProject> call, Throwable t) {
                }
            });
    }
    private void getMovies(String query) {
        ApiUtils.getService().getMovieQuery(query).enqueue(new Callback<MovieProject>() {
            @Override
            public void onResponse(Call<MovieProject> call, Response<MovieProject> response) {
                if (response.isSuccessful()) {
                    movieListAdapter.updateMovies(response.body().getResults());
                } else {

                }
            }
            @Override
            public void onFailure(Call<MovieProject> call, Throwable t) {

            }
        });
    }

    public static void getMovieDetail(Long movieId) {
        ApiUtils.getService().getMovieDetail(movieId).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if (response.isSuccessful()) {
                    detailResponse = response.body();

                } else {

                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {


            }
        });
    }


    @Override
    public void onItemClicked(Long movieId) {
        detailFragment = new DetailFragment();
        getMovieDetail(movieId);
        if(detailResponse != null){
            detailFragment.updateResponse(detailResponse);
            getFragmentManager().beginTransaction().
                    replace(R.id.mainContainer,detailFragment).addToBackStack("").commit();
        }


    }

    @Override
    public boolean onQueryTextSubmit(String mQuery) {
        query = mQuery;
        getMovies(query);
        searchView.setVisibility(View.GONE);
        return true;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        query = newText;
        return true;
    }


}
