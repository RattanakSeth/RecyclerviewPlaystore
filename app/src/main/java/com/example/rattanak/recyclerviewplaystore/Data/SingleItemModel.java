package com.example.rattanak.recyclerviewplaystore.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rattanak on 6/30/17.
 */

public class SingleItemModel {
    @SerializedName("_id")
    private int id;

    @SerializedName("_book_name")
    private String book_name;

    @SerializedName("_author_name")
    private String author_name;

    @SerializedName("_thumbnail_url")
    private String thumbnailUrl;

    @SerializedName("_description")
    private String description;

    public SingleItemModel(String book_name, String thumbnailUrl){
        this.book_name = book_name;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
