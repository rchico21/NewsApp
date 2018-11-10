package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private TextView mSearchBoxEditText;
    private ProgressBar mProgressBar;
    private TextView mUrlDisplay;
    private static final String TAG = "MainActivity";

    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private ArrayList<NewsItem> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSearchBoxEditText = (TextView) findViewById(R.id.publishedAt);
        mRecyclerView = (RecyclerView) findViewById(R.id.news_recyclerview);
        mAdapter = new NewsRecyclerViewAdapter(this, news);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatWasClickedId = item.getItemId();
        if(itemThatWasClickedId == R.id.action_search){
            URL url = NetworkUtils.buildUrl();
            NewsQueryTask task = new NewsQueryTask();
            task.execute(url);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class NewsQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL...urls){
            String articleResults = "";

            try{
                articleResults = NetworkUtils.getResponseFromHttpUrl(urls[0]);
            }catch (IOException e){
                e.printStackTrace();
            }
            return articleResults;
        }

        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            news = JsonUtils.parseNews(s);
            mAdapter.newsArray.addAll(news); //Add all values into adapter
            mAdapter.notifyDataSetChanged(); //Display live changes
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
