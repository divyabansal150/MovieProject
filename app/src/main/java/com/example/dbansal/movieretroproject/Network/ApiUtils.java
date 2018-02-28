package com.example.dbansal.movieretroproject.Network;

/**
 * Created by d.bansal on 21/1/18.
 */

public class ApiUtils {
    static final String BASE_URL = "https://api.themoviedb.org/3/";

    public static ServiceModule getService(){

        return RetrofitClient.getClient(BASE_URL).create(ServiceModule.class);

    }
}
