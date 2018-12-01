package com.example.rkjc.news_app_2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsItemViewHolder> {
    Context mContext;
    List<NewsItem> newsArray;

    public NewsRecyclerViewAdapter(Context context, List<NewsItem> newsItems){
        this.mContext = context;
        this.newsArray = newsItems;
    }
//
//    @Override
//    public void getView(int position, View convertView, ViewGroup parent) {
//        SquaredImageView view = (SquaredImageView) convertView;
//        if (view == null) {
//            view = new SquaredImageView(mContext);
//        }
//        String url = getItem(position);
//
//        Picasso.get().load(url).into(view);
//    }

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
//        NewsItem newsItem = newsArray.get(position);
//        holder.author.setText(newsItem.getAuthor());
//        holder.title.setText(newsItem.getTitle());
//        holder.publishedAt.setText(newsItem.getPublishedAt());
//        holder.description.setText(newsItem.getDescription());
//        holder.url.setText(newsItem.getUrl());
//       // holder.image.setImageDrawable(newsItem.getUrlToImage());
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageViewProduct);
//        Picasso.with(mContext).load(newsItem.getUrlToImage()).into(image);
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
        ImageView image;
        TextView publishedAt;

        public NewsItemViewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            url = (TextView) itemView.findViewById(R.id.url);
            image = (ImageView) itemView.findViewById(R.id.urlToImage);
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
            url.setText(newsArray.get(listIndex).getUrl());
            Picasso.with(mContext).load(newsArray.get(listIndex).getUrlToImage()).into(image);


        }
    }

}
