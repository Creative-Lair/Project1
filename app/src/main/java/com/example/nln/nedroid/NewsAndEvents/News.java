package com.example.nln.nedroid.NewsAndEvents;

/**
 * Created by NLN on 8/1/2016.
 */
public class News {

    private String name1;
    private int pic1;
    private String name;
    private int thumbnail;

    public News() {
    }

    public News(int pic1, String name1, String name, int thumbnail) {
//    public News(String name, int thumbnail) {
        this.pic1 = pic1;
        this.name1 = name1;
        this.name = name;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public int getPic1() {
        return pic1;
    }

}