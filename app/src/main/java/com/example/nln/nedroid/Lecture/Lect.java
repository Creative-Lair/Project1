package com.example.nln.nedroid.Lecture;

/**
 * Created by NLN on 8/14/2016.
 */
public class Lect {
    private String name;
    private String url;

    public Lect() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Lect(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
