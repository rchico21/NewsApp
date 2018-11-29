package com.example.rkjc.news_app_2;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Query("SELECT * FROM news_item ORDER BY id ASC")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Insert
    void insert(List<NewsItem> item);

    @Query("DELETE FROM news_item")
    void deleteAllItems();

}
