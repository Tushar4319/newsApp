package com.androtush.newsapp.View;

import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;

public interface EveryThingView {

    void onSuccess(TopHeadlineNewsResponse everyThingNewsResponse);

    void onFailuar(String message);
}
