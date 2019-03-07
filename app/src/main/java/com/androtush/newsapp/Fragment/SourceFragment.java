package com.androtush.newsapp.Fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.androtush.newsapp.Model.SourceModel;
import com.androtush.newsapp.R;
import com.androtush.newsapp.Response.SourceNewsResponse;
import com.androtush.newsapp.Utils.IoUtils;
import com.androtush.newsapp.View.SourceView;

import java.util.ArrayList;


public class SourceFragment extends Fragment implements SourceView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SourceModel sourceModel;
    private RecyclerView mTopHeadlineRecyclerView;
    private TopHeadLineAdapter mAdapter;
    private ProgressDialog dialog;

    public SourceFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SourceFragment newInstance(String param1, String param2) {
        SourceFragment fragment = new SourceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_everything, container, false);

        inti(view);

        return view;
    }

    private void inti(View view) {
        dialog = IoUtils.getProgressDialog(getContext());
        dialog.show();
        sourceModel = new SourceModel(this);
        sourceModel.getSource("2f5d94003b614fc29af38e4356e4364f");
        mTopHeadlineRecyclerView = (RecyclerView) view.findViewById(R.id.top_headlinerecycler_view);
    }


    @Override
    public void onSuccess(SourceNewsResponse sourceNewsResponse) {
        dialog.dismiss();
        //Toast.makeText(getContext(),"Source Response Received",Toast.LENGTH_SHORT).show();
        mAdapter = new TopHeadLineAdapter(sourceNewsResponse.getSources());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mTopHeadlineRecyclerView.setLayoutManager(mLayoutManager);
        mTopHeadlineRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mTopHeadlineRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onFailuar(String message) {
        dialog.dismiss();
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }


    public class TopHeadLineAdapter extends RecyclerView.Adapter<TopHeadLineAdapter.TopHeadlineViewHolder> {

        private ArrayList<SourceNewsResponse.Sources> articles;

        public class TopHeadlineViewHolder extends RecyclerView.ViewHolder {
            private TextView mTxtTitle,mTxtDescription,mTxtAuthor;
            public TopHeadlineViewHolder(View view) {
                super(view);
                mTxtTitle = (TextView) view.findViewById(R.id.txtTitle);
                mTxtDescription = (TextView) view.findViewById(R.id.txtDescription);
                mTxtAuthor = (TextView) view.findViewById(R.id.txtAuthor);

            }
        }


        public TopHeadLineAdapter(ArrayList<SourceNewsResponse.Sources> articles) {
            this.articles = articles;
        }

        @Override
        public TopHeadLineAdapter.TopHeadlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.source_list_row, parent, false);

            return new TopHeadLineAdapter.TopHeadlineViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(TopHeadLineAdapter.TopHeadlineViewHolder holder, int position) {
            final SourceNewsResponse.Sources sources = articles.get(position);
            holder.mTxtTitle.setText(sources.getName());
            holder.mTxtDescription.setText(sources.getDescription());
            holder.mTxtAuthor.setText(sources.getUrl());
        }

        @Override
        public int getItemCount() {
            return articles.size();
        }
    }

}
