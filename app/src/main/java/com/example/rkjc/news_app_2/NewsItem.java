package com.example.rkjc.news_app_2;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.ColumnInfo;
import android.graphics.drawable.Drawable;


@Entity(tableName = "news_item")
public class NewsItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Author")
    private String author;

    @ColumnInfo(name = "Title")
    private String title;

    @ColumnInfo(name = "Description")
    private String description;

    @ColumnInfo(name = "Url")
    private String url;

    @ColumnInfo(name = "urlToImage")
    private String urlToImage;

    @ColumnInfo(name = "Publish Date")
    private String publishedAt;


    public NewsItem(String author, String title, String description, String url, String urlToImage, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    @Ignore
    public NewsItem(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage(){
        return urlToImage;
    }

    public void setUrlImage(String urlImage){
        this.urlToImage = urlImage;
    }

    public String getPublishedAt(){
        return publishedAt;
    }

    public  void setpublishedAt(String date){
        this.publishedAt = publishedAt;
    }
}
