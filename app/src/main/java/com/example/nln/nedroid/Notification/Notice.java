package com.example.nln.nedroid.Notification;

/**
 * Created by NLN on 8/1/2016.
 */
public class Notice {

    private String date;
    private String title;
    private String desciption;
    private int thumbnail;

    public Notice() {
    }

    public Notice(String  date, String title, String desciption, int thumbnail) {
//    public News(String name, int thumbnail) {
        this.date = date;
        this.title = title;
        this.desciption = desciption;
        this.thumbnail = thumbnail;
    }

    public String getDate() {
        return date;
    }

    public void seDate(String date) {
        this.date = date;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void seTitle(String title) {
        this.title = title;
    }
    public String getDesciption() {
        return desciption;
    }

    public void seDescription(String description) {
        this.desciption= description;
    }
}