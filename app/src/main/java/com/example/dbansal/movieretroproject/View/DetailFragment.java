package com.example.dbansal.movieretroproject.View;



import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by d.bansal on 27/2/18.
 */


public class DetailFragment extends Fragment {
    public TextView title,status,releaseDate,overview;
    SimpleDraweeView imageView;
    MovieDetail responseBody;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_layout,container,false);
        initializeViews(view);
        setData();
        return view;
    }


    private void setData() {
        if(responseBody != null) {
            title.setText("Title: "+responseBody.getTitle().toString());
            status.setText("Status: "+responseBody.getStatus().toString());
            releaseDate.setText("Release Date: "+responseBody.getReleaseDate().toString());
            overview.setText("Overview: "+responseBody.getOverview().toString());

            String imagesPath = responseBody.getPosterPath();

            Uri imageUri = Uri.parse("https://image.tmdb.org/t/p/w185_and_h278_bestv2"+imagesPath);
            imageView.setImageURI(imageUri);

        }

    }

    private void initializeViews(View view) {
        imageView = (SimpleDraweeView)view.findViewById(R.id.sdvImage);
        title = (TextView)view.findViewById(R.id.titleText);
        status = (TextView)view.findViewById(R.id.statusText);
        releaseDate = (TextView)view.findViewById(R.id.releaseDateText);
        overview = (TextView)view.findViewById(R.id.overviewText);
    }


    public void updateResponse(MovieDetail body) {
        responseBody = body;

    }
}
