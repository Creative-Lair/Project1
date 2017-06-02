package com.example.nln.nedroid.NewsAndEvents;

/**
 * Created by NLN on 8/1/2016.
 */
public class News {

    private String title;
    private String description;
    private String creatorId;
    private String[] photos;

    public News(String title, String description, String creatorId, String[] photos) {
        this.title = title;
        this.description = description;
        this.creatorId = creatorId;
        this.photos = photos;
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

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String[] getPhotos() {
        return photos;
    }

    public void setPhotos(String[] photos) {
        this.photos = photos;
    }
}