package com.example.nln.nedroid.Helper;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Student {

    private String password;
    private String name;
    private long semester;
    private String photourl;

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public long getSemester() {
        return semester;
    }

    public void setSemester(long semester) {
        this.semester = semester;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public Student(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public Student() {}
}
