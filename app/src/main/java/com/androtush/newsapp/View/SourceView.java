package com.androtush.newsapp.View;

import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.SourceNewsResponse;

public interface SourceView {

    void onSuccess(SourceNewsResponse sourceNewsResponse);

    void onFailuar(String message);
}
