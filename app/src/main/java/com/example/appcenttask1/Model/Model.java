package com.example.appcenttask1.Model;

import android.media.Image;
import android.widget.AbsListView;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;

public class Model {
    //The picture, title, description, date and source information of the result will be displayed for each
    //result.


    @SerializedName("title")
    String title;
    @SerializedName("description")
    String description;
    @SerializedName("publishedAt")
    String publishedAt;
    @SerializedName("urlToImage")
    String urlToImage;
    @SerializedName("source")
    Source source;

    public Model(String title, String description, String publishedAt, String urlToImage, Source source) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.urlToImage = urlToImage;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
