package com.androtush.newsapp.Model;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Fragment.TopHeadlinesFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Presenter.TopHeadlinePresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.TopHeadlineView;

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

        NewsApp.getApiClient().getRestInterface().getTopHeadlineNews(country,apiKey).enqueue(new Callback<TopHeadlineNewsResponse>() {
            @Override
            public void onResponse(Call<TopHeadlineNewsResponse> call, Response<TopHeadlineNewsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("ok")){
                        topHeadlineView.onSuccess(response.body());
                    }else {
                        topHeadlineView.onFailuar("Please try again");
                    }
                }
            }

            @Override
            public void onFailure(Call<TopHeadlineNewsResponse> call, Throwable t) {
                topHeadlineView.onFailuar("Please Try Again");
            }
        });

    }
}
