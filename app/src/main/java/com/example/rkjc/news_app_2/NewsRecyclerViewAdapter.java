package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {
    Context mContext;
    ArrayList<NewsItem> newsArray;

    public NewsRecyclerViewAdapter(Context context, ArrayList<NewsItem> news){
        this.mContext = context;
        this.newsArray = news;
    }

    @Override
    public NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.news_item, parent, shouldAttachToParentImmediately);
        NewsItemViewHolder viewHolder = new NewsItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsRecyclerViewAdapter.NewsItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return newsArray.size();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView description;
        TextView url;
        TextView publishedAt;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            url = (TextView) itemView.findViewById(R.id.url);
            publishedAt = (TextView) itemView.findViewById(R.id.publishedAt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                    browserIntent.setData(Uri.parse(url.getText().toString()));
                    mContext.startActivity(browserIntent);
                }
            });
        }

        void bind(final int listIndex) {
            title.setText("Title" + newsArray.get(listIndex).getTitle());
            description.setText("Description" + newsArray.get(listIndex).getDescription());
            url.setText("Url" + newsArray.get(listIndex).getUrl());
            publishedAt.setText("PublishedAt" + newsArray.get(listIndex).getpublishedAt());


        }

    }
}
