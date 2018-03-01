package com.example.dbansal.movieretroproject.View;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.Model.MovieProject;

/**
 * Created by d.bansal on 1/3/18.
 */

public interface INetworkCall {
   public  void  handleResponse(MovieProject value);
   public  void handleError(Throwable error);

   void handleDetailResponse(MovieDetail value);
}
