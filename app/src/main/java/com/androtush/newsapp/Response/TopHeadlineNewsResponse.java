package com.androtush.newsapp.Response;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class TopHeadlineNewsResponse implements Parcelable {

    private String totalResults;

    private ArrayList<Articles> articles;

    private String status;

    protected TopHeadlineNewsResponse(Parcel in) {
        totalResults = in.readString();
        status = in.readString();
    }

    public static final Creator<TopHeadlineNewsResponse> CREATOR = new Creator<TopHeadlineNewsResponse>() {
        @Override
        public TopHeadlineNewsResponse createFromParcel(Parcel in) {
            return new TopHeadlineNewsResponse(in);
        }

        @Override
        public TopHeadlineNewsResponse[] newArray(int size) {
            return new TopHeadlineNewsResponse[size];
        }
    };

    public String getTotalResults ()
    {
        return totalResults;
    }

    public void setTotalResults (String totalResults)
    {
        this.totalResults = totalResults;
    }

    public ArrayList<Articles> getArticles ()
    {
        return articles;
    }

    public void setArticles (ArrayList<Articles> articles)
    {
        this.articles = articles;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [totalResults = "+totalResults+", articles = "+articles+", status = "+status+"]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(totalResults);
        parcel.writeString(status);
    }


}
