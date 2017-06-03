package com.example.nln.nedroid.NewsAndEvents;

import java.util.ArrayList;

/**
 * Created by NLN on 8/1/2016.
 */
public class News {

    private String title;
    private String description;
    private String username;
    private String photo_user;
    private String coverPhoto;
    private boolean verify;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public News(String title, String description, String username, String photo_user, String coverPhoto, boolean verify, ArrayList<String> photos) {
        this.title = title;
        this.description = description;
        this.username = username;
        this.photo_user = photo_user;
        this.coverPhoto = coverPhoto;
        this.verify = verify;
        this.photos = photos;
    }

    public News() {
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

    public boolean isVerify() {
        return verify;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoto_user() {
        return photo_user;
    }

    public void setPhoto_user(String photo_user) {
        this.photo_user = photo_user;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public ArrayList<String> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<String> photos) {
        this.photos = photos;
    }

    private ArrayList<String> photos;

}