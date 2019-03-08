package com.androtush.newsapp.Model;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Services.ApiClient;
import com.androtush.newsapp.Services.ApiInterface;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.EveryThingView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EveryThingModel implements EveryThingPresenter {

    private EveryThingView everyThingView;

    public EveryThingModel(EverythingFragment everythingFragment){
        this.everyThingView = everythingFragment;
    }

    @Override
    public void getEverything(String q, String from, String sortBy, String apiKey) {

        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Observable<TopHeadlineNewsResponse> cryptoObservable = apiService.getEverythingNews(q,from,sortBy,apiKey);
        cryptoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);

    }

    private void handleResults(TopHeadlineNewsResponse marketList) {
        if (marketList.getStatus().equals("ok")) {
            everyThingView.onSuccess(marketList);
        }else {
            everyThingView.onFailuar("Please try again");
        }
    }

    private void handleError(Throwable t) {
        everyThingView.onFailuar("Please try again");
    }
}
