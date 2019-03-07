package com.androtush.newsapp.Model;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.EveryThingView;

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

        NewsApp.getApiClient().getRestInterface().getEverythingNews(q,from,sortBy,apiKey).enqueue(new Callback<EveryThingNewsResponse>() {
            @Override
            public void onResponse(Call<EveryThingNewsResponse> call, Response<EveryThingNewsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("ok")){
                        everyThingView.onSuccess(response.body());
                    }else {
                        everyThingView.onFailuar("Please try again");
                    }
                }
            }

            @Override
            public void onFailure(Call<EveryThingNewsResponse> call, Throwable t) {
                everyThingView.onFailuar("Please Try Again");
            }
        });

    }
}
