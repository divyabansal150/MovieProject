package com.example.dbansal.movieretroproject.Network;

import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.Model.MovieProject;
import com.example.dbansal.movieretroproject.Model.Results;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by d.bansal on 21/1/18.
 */

public interface ServiceModule {
    // https://api.themoviedb.org/3/movie/now_playing?api_key=f883c72df9dd13e5493cd9ebd887ec8d
    @GET("/3/movie/{type}?api_key=f883c72df9dd13e5493cd9ebd887ec8d")
    Call<MovieProject> getMovie(@Path("type") String type);

    //https://api.themoviedb.org/3/movie/550?api_key=f883c72df9dd13e5493cd9ebd887ec8d
    @GET("/3/movie/{Id}?api_key=f883c72df9dd13e5493cd9ebd887ec8d")
    Call<MovieDetail> getMovieDetail(@Path("Id") long hotelId);

    //https://api.themoviedb.org/3/search/movie?api_key=f883c72df9dd13e5493cd9ebd887ec8d
    // &query=fifty&language=en-US&page=1&include_adult=false

    @GET("/3/search/movie?api_key=f883c72df9dd13e5493cd9ebd887ec8d")
    Call<MovieProject> getMovieQuery(@Query("query") String query);

}
