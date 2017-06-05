package com.example.nln.nedroid.Notification;

/**
 * Created by NLN on 8/1/2016.
 */
public class Notice {

    private String date;
    private String title;
    private String description;
    private String photo;

    public Notice() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Notice(String date, String title, String description, String photo) {
        this.date = date;
        this.title = title;
        this.description = description;
        this.photo = photo;
    }
}