package com.example.rkjc.news_app_2;


import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class NewsItemRepository {
    private NewsItemDao newsItemDao;
    private LiveData<List<NewsItem>> allItems;
    private Application application;

    public NewsItemRepository(Application application){
        NewsItemDatabase db = NewsItemDatabase.getDatabase(application.getApplicationContext());
        this.application = application;
        newsItemDao = db.newsItemDao();
        allItems = newsItemDao.loadAllNewsItems();

    }

    public LiveData<List<NewsItem>> loadAllNewsItems(){
        new getAllAsyncTask(newsItemDao, this).execute();
        return allItems;
    }

    public List<NewsItem> syncDataBase(){
        try{
            return new syncDatabase(newsItemDao, this, this.application).execute().get();
        }catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }
        return null;
    }

    public void setNewItems(LiveData<List<NewsItem>> items){
        this.allItems = items;
    }

    public static class getAllAsyncTask extends AsyncTask<Void, Void, LiveData<List<NewsItem>>>{
        private NewsItemDao  newsItemDao;
        private NewsItemRepository newsItemRepository;

        getAllAsyncTask(NewsItemDao newsItemDao, NewsItemRepository newsItemRepository){
            this.newsItemDao = newsItemDao;
            this.newsItemRepository = newsItemRepository;
        }

        @Override
        protected LiveData<List<NewsItem>> doInBackground(Void... avoid){
            return this.newsItemDao.loadAllNewsItems();
        }

        @Override
        protected void onPostExecute(LiveData<List<NewsItem>> listLiveData){
            super.onPostExecute(listLiveData);
            this.newsItemRepository.setNewItems(listLiveData);
        }


    }

    public static class syncDatabase extends AsyncTask<Void, Void, List<NewsItem>>{
        private NewsItemDao newsItemDao;
        private NewsItemRepository newsItemRepository;
        private Application application;

        syncDatabase(NewsItemDao newsItemDao, NewsItemRepository newsItemRepository, Application application){
            this.application = application;
            this.newsItemDao = newsItemDao;
            this.newsItemRepository = newsItemRepository;
        }

        @Override
        protected List<NewsItem> doInBackground(Void... voids){
            String jsonString = null;
            List<NewsItem> items = null;

            try{
                jsonString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
                items = JsonUtils.parseNews(jsonString);
                newsItemDao.deleteAllItems();
                newsItemDao.insert(items);
            } catch (IOException e){
                e.printStackTrace();
            }
            return items;
        }
    }

}
