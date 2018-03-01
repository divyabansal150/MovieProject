package com.example.dbansal.movieretroproject.Network;

import android.support.compat.BuildConfig;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dbansal.movieretroproject.Network.ApiUtils.BASE_URL;

/**
 * Created by d.bansal on 21/1/18.
 */

public class RetrofitClient {
    static Retrofit retrofit;

    public static Retrofit getClient(String url) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


        }
        return retrofit;
    }
}
