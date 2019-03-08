package com.androtush.newsapp.Services;


import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.SourceNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"Accept: application/json"})
    @GET("/v2/everything")
    Observable<TopHeadlineNewsResponse> getEverythingNews(@Query("q") String keyword,
                                                   @Query("from") String fromDate,
                                                   @Query("sortBy") String sortBy,
                                                   @Query("apiKey") String apiKey);

    @Headers({"Accept: application/json"})
    @GET("/v2/sources")
    Observable<SourceNewsResponse> getSourceNews(@Query("apiKey") String apiKey);


    @Headers({"Accept: application/json"})
    @GET("/v2/top-headlines")
    Observable<TopHeadlineNewsResponse> getTopHeadlineNews(@Query("country") String country,
                                                                 @Query("apiKey") String apiKey);

}