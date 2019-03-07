package com.androtush.newsapp.Model;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Fragment.SourceFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Presenter.SourcePresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.SourceNewsResponse;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.SourceView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourceModel implements SourcePresenter {

    private SourceView sourceView;

    public SourceModel(SourceFragment sourceFragment){
        this.sourceView = sourceFragment;
    }

    @Override
    public void getSource(String apiKey) {

        NewsApp.getApiClient().getRestInterface().getSourceNews(apiKey).enqueue(new Callback<SourceNewsResponse>() {
            @Override
            public void onResponse(Call<SourceNewsResponse> call, Response<SourceNewsResponse> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus().equals("ok")){
                        sourceView.onSuccess(response.body());
                    }else {
                        sourceView.onFailuar("Please try again");
                    }
                }
            }

            @Override
            public void onFailure(Call<SourceNewsResponse> call, Throwable t) {
                sourceView.onFailuar("Please Try Again");
            }
        });

    }
}
