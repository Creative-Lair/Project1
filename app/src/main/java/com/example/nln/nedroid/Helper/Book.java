package com.example.nln.nedroid.Helper;

/**
 * Created by AHSAN on 6/4/2017.
 */

public class Book {

    String name;
    String url;
    String type;

    public Book(){

    }

    public Book(String name, String url, String type) {
        this.name = name;
        this.url = url;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
