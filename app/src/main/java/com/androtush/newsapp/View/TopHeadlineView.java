package com.androtush.newsapp.View;

import com.androtush.newsapp.Response.SourceNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;

public interface TopHeadlineView {

    void onSuccess(TopHeadlineNewsResponse topHeadlineNewsResponse);

    void onFailuar(String message);
}
