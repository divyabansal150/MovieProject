package com.example.dbansal.movieretroproject.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dbansal.movieretroproject.R;

/**
 * Created by d.bansal on 1/3/18.
 */

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private SearchView searchView;
    private String query;
    private MovieListFragment mFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_layout,container,false);
        searchView = (SearchView) view.findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(this);

        return view;
    }

    @Override
    public boolean onQueryTextSubmit(String mQuery) {
        query = mQuery;
        Bundle b = new Bundle();
        b.putString("query",query);
        MovieListFragment frag = new MovieListFragment();
        frag.setArguments(b);

        getFragmentManager().beginTransaction().replace(R.id.mainContainer,frag).addToBackStack("").commit();

        return true;
    }


    @Override
    public boolean onQueryTextChange(String newText) {
        query = newText;
        return true;
    }

}
