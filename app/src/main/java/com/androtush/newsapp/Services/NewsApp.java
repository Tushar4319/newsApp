package com.androtush.newsapp.Services;

import android.app.Application;

/**
 * Created by acer on 19/07/2016.
 */
public class NewsApp extends Application {

    private static ApiClient apiClient;

    @Override
    public void onCreate() {
        super.onCreate();
        getApiClient();
    }

    public static ApiClient getApiClient() {
        if(apiClient == null){
            apiClient = new ApiClient();
        }
        return  apiClient;
    }


}
