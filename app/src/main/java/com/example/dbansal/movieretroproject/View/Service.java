package com.example.dbansal.movieretroproject.View;

import com.example.dbansal.movieretroproject.Model.MovieDetail;
import com.example.dbansal.movieretroproject.Model.MovieProject;
import com.example.dbansal.movieretroproject.Network.ApiUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d.bansal on 1/3/18.
 */

public  class Service {

    public static void getMovie(String type,INetworkCall iNetworkCall,Boolean query) {

        Observable<MovieProject> call = null;
        if(query) {
            call = ApiUtils.getService().getMovieQuery(type);
        }else{
            call = ApiUtils.getService().getMovie(type);
        }
        call
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
               // .subscribe(MovieListFragment::handleResponse,MovieListFragment::handleError);
        .subscribeWith(new Observer<MovieProject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(MovieProject value) {

                iNetworkCall.handleResponse(value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public static void getMovies(String query,INetworkCall iNetworkCall) {

        Observable<MovieProject> call = ApiUtils.getService().getMovieQuery(query);
        call
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
               // .subscribe(MovieListFragment::handleResponse,MovieListFragment::handleError);
                .subscribeWith(new Observer<MovieProject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieProject value) {

                        iNetworkCall.handleResponse(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static void getMovieDetail(Long movieId,INetworkCall iNetworkCall) {
        Observable<MovieDetail> call = ApiUtils.getService().getMovieDetail(movieId);
        call
                .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
                .observeOn(AndroidSchedulers.mainThread())
              //  .subscribe(DetailFragment::handleResponse,DetailFragment::handleError);
                .subscribeWith(new Observer<MovieDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieDetail value) {

                        iNetworkCall.handleDetailResponse(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
