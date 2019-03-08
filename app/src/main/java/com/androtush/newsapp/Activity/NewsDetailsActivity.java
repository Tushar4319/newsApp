package com.androtush.newsapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androtush.newsapp.R;
import com.androtush.newsapp.Response.Articles;
import com.androtush.newsapp.Response.TopHeadlineNewsResponse;
import com.androtush.newsapp.Utils.DateUtils;
import com.bumptech.glide.Glide;

import java.text.BreakIterator;
import java.util.ArrayList;

public class NewsDetailsActivity extends AppCompatActivity {

    private ArrayList<Articles> articles;
    private Articles article;
    private ImageView mImgPhoto;
    private TextView mTxtTitle, mTxtDescription, mTxtMoreInfo;
    private TextView mTxtAuthor, mTxtPostedOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        articles =  getIntent().getParcelableArrayListExtra("newsDetails");
        article = articles.get(getIntent().getIntExtra("position",0));
        init();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle(article.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void init() {

        mImgPhoto = (ImageView) findViewById(R.id.imgPhoto);
        mTxtTitle = (TextView) findViewById(R.id.txtTitle);
        mTxtDescription = (TextView) findViewById(R.id.txtDescription);
        mTxtMoreInfo = (TextView) findViewById(R.id.txtMoreInfo);
        mTxtAuthor = (TextView) findViewById(R.id.txtAuthor);
        mTxtPostedOn = (TextView) findViewById(R.id.txtPostedOn);

        mTxtTitle.setText(article.getTitle());
        mTxtDescription.setText(article.getDescription());
        mTxtMoreInfo.setText(article.getContent());

        if(article.getAuthor() != null) {
            mTxtAuthor.setText(article.getAuthor());
        }else{
            try {
                if (article.getSource().getName() != null)
                    mTxtAuthor.setText(article.getSource().getName());
            }catch (Exception e){
                e.printStackTrace();
                mTxtAuthor.setVisibility(View.GONE);
            }
        }
        String removeT = article.getPublishedAt().replaceFirst("T"," ");
        String remoceZ = removeT.replace("Z","");

        mTxtPostedOn.setText(DateUtils.getTimeAgo(remoceZ,NewsDetailsActivity.this));


        Glide.with(NewsDetailsActivity.this).load(article.getUrlToImage()).into(mImgPhoto);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                //onBackPressed();
                finish();
                break;
/*            case R.id.action_edit:
                Intent intent = new Intent(mContext, AddNewJobActivity.class);
                intent.putExtra("From","Edit Job");
                mContext.startActivity(intent);
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }

}
