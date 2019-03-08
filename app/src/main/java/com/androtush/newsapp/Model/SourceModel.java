package com.androtush.newsapp.Model;

import com.androtush.newsapp.Fragment.EverythingFragment;
import com.androtush.newsapp.Fragment.SourceFragment;
import com.androtush.newsapp.Presenter.EveryThingPresenter;
import com.androtush.newsapp.Presenter.SourcePresenter;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.SourceNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Services.ApiClient;
import com.androtush.newsapp.Services.ApiInterface;
import com.androtush.newsapp.Services.NewsApp;
import com.androtush.newsapp.View.SourceView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

        ApiInterface apiService = ApiClient.getClient()
                .create(ApiInterface.class);

        Observable<SourceNewsResponse> cryptoObservable = apiService.getSourceNews(apiKey);
        cryptoObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);

    }

    private void handleResults(SourceNewsResponse sourceNewsResponse) {
        if (sourceNewsResponse.getStatus().equals("ok")) {
            sourceView.onSuccess(sourceNewsResponse);
        }else {
            sourceView.onFailuar("Please try again");
        }
    }

    private void handleError(Throwable t) {
        sourceView.onFailuar("Please try again");
    }
}
