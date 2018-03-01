package com.example.dbansal.movieretroproject.View;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dbansal.movieretroproject.Model.MovieProject;
import com.example.dbansal.movieretroproject.Model.Results;

import java.util.ArrayList;
import java.util.List;

import com.example.dbansal.movieretroproject.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by d.bansal on 26/2/18.
 */

class MovieListAdapter extends RecyclerView.Adapter <MovieListAdapter.MovieListViewHolder> {
    private final FragmentActivity mContext;
    private List<Results> mResults;
    private IOnItemClickInterface fragmentInstance;

    public MovieListAdapter(IOnItemClickInterface fragmentInstance, ArrayList<Results> articles, FragmentActivity activity) {
        this.fragmentInstance = fragmentInstance;
        mContext = activity;
        mResults = articles;
    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapterlayout,parent,false);
        MovieListViewHolder vh = new MovieListViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MovieListViewHolder holder, int position) {

        final Results movie = mResults.get(position);
        holder.textView.setText(movie.getTitle());

      //  https://image.tmdb.org/t/p/w185_and_h278_bestv2/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg
      //  https://api.themoviedb.org/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg
        String imagesPath = movie.getPosterPath();

        Uri imageUri = Uri.parse("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+imagesPath);
        holder.imageView.setImageURI(imageUri);
//        holder.ratingBar.setRating(movie.getVoteAverage().floatValue());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentInstance.onItemClicked(Long.valueOf(movie.getId()));

            }
        });
    }


    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void setListener(IOnItemClickInterface topicListFragment) {
        fragmentInstance  = topicListFragment;
    }



    public void updateMovies(List<Results> results) {
        mResults = results;
        notifyDataSetChanged();
    }



    class MovieListViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        SimpleDraweeView imageView;
        LinearLayout linearLayout;
        AppCompatRatingBar ratingBar;
        public MovieListViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.listText);
            imageView = (SimpleDraweeView)itemView.findViewById(R.id.sdvImage);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.listLayout);
          //  ratingBar = (AppCompatRatingBar)itemView.findViewById(R.id.ratingBar);
        }
    }

    public interface IOnItemClickInterface {
        void onItemClicked(Long movieId);
    }
}
