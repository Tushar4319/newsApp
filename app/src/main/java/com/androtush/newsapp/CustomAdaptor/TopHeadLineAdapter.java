package com.androtush.newsapp.CustomAdaptor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androtush.newsapp.Activity.NewsDetailsActivity;
import com.androtush.newsapp.R;
import com.androtush.newsapp.Response.Articles;
import com.androtush.newsapp.Response.EveryThingNewsResponse;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Utils.DateUtils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TopHeadLineAdapter extends RecyclerView.Adapter<TopHeadLineAdapter.TopHeadlineViewHolder> {

    private Context context;
    private ArrayList<Articles> articles;
    private Activity activity;

    public class TopHeadlineViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxtTitle,mTxtDescription,mTxtAuthor,mTxtPostedOn,mTxtShare;
        private ImageView mImgPhoto;
        private CardView mCardRow;
        public TopHeadlineViewHolder(View view) {
            super(view);
            mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
            mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
            mTxtAuthor = (TextView) view.findViewById(R.id.txtAuthor);
            mTxtPostedOn = (TextView) view.findViewById(R.id.txtPostedOn);
            mTxtShare = (TextView) view.findViewById(R.id.txtShare);
            mImgPhoto = (ImageView) view.findViewById(R.id.imgPhoto);
            mCardRow = (CardView) view.findViewById(R.id.cardRow);
        }
    }


    public TopHeadLineAdapter(ArrayList<Articles> articles, Context context, Activity activity) {
        this.articles = articles;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public TopHeadLineAdapter.TopHeadlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_list_row, parent, false);

        return new TopHeadLineAdapter.TopHeadlineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopHeadLineAdapter.TopHeadlineViewHolder holder, int position) {
        Articles article = articles.get(position);
        holder.mTxtTitle.setText(article.getTitle());
        holder.mTxtDescription.setText(article.getDescription());
        if(article.getAuthor() != null) {
            holder.mTxtAuthor.setText(article.getAuthor());
        }else{
            holder.mTxtAuthor.setText(article.getSource().getName());

        }
        String removeT = article.getPublishedAt().replaceFirst("T"," ");
        String remoceZ = removeT.replace("Z","");

        holder.mTxtPostedOn.setText(DateUtils.getTimeAgo(remoceZ,context));

        holder.mTxtShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        article.getTitle()+" \nfor more details "+article.getUrl());
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });

        holder.mCardRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, NewsDetailsActivity.class);
                intent.putParcelableArrayListExtra("newsDetails",articles);
                intent.putExtra("position",position);
                activity.startActivity(intent);
            }
        });

        Glide.with(context).load(article.getUrlToImage()).into(holder.mImgPhoto);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}

