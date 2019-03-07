package com.androtush.newsapp.View;

import com.androtush.newsapp.Response.EveryThingNewsResponse;

public interface EveryThingView {

    void onSuccess(EveryThingNewsResponse everyThingNewsResponse);

    void onFailuar(String message);
}
