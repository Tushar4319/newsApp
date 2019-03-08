package com.androtush.newsapp.Model;

import android.content.Context;
import android.util.Log;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Fragment.TopHeadlinesFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Presenter.TopHeadlinePresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Services.ApiClient;
import com.androtush.newsapp.Services.ApiInterface;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.TopHeadlineView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopHeadlineModel implements TopHeadlinePresenter {

    private TopHeadlineView topHeadlineView;

    public TopHeadlineModel(TopHeadlinesFragment topHeadlinesFragment){
        this.topHeadlineView = topHeadlinesFragment;
    }

    @Override
    public void getTopHeadline(String country, String apiKey) {


        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Observable<TopHeadlineNewsResponse> cryptoObservable = apiService.getTopHeadlineNews(country,apiKey);
        cryptoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);


    }

    private void handleResults(TopHeadlineNewsResponse marketList) {
        if (marketList.getStatus().equals("ok")) {
            topHeadlineView.onSuccess(marketList);
        }else {
            topHeadlineView.onFailuar("Please try again");
        }
    }

    private void handleError(Throwable t) {
        topHeadlineView.onFailuar("Please try again");
    }

}
