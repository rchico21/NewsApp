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
import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {
    Context mContext;
    List<NewsItem> newsArray;

    public NewsRecyclerViewAdapter(Context context, List<NewsItem> newsItems){
        this.mContext = context;
        this.newsArray = newsItems;
    }

    @Override
    public NewsRecyclerViewAdapter.NewsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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

    public void setNewsItems(List<NewsItem> items){
        this.newsArray = items;
        notifyDataSetChanged();
    }

    public class NewsItemViewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView title;
        TextView description;
        TextView url;
        //   TextView urlImage;
        TextView publishedAt;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            url = (TextView) itemView.findViewById(R.id.url);
            //       urlImage = (TextView) itemView.findViewById(R.id.urlImage);
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
            author.setText("Author: \n" + newsArray.get(listIndex).getAuthor());
            title.setText("Title: \n" + newsArray.get(listIndex).getTitle());
            description.setText("Description: \n" + newsArray.get(listIndex).getDescription());
            publishedAt.setText("PublishedAt: \n" + newsArray.get(listIndex).getPublishedAt());
            //       urlImage.setText("Image: \n" + newsArray.get(listIndex).getUrlImage());
            url.setText(newsArray.get(listIndex).getUrl());

        }
    }

}
