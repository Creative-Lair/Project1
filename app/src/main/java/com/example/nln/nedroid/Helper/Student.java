package com.example.nln.nedroid.Helper;

import java.util.ArrayList;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Student {

    private String password;
    private String name;
    private long semester;
    private String photourl;
    private ArrayList<Long> courses;

    public Student(String password, String name, long semester, String photourl, ArrayList<Long> courses) {
        this.password = password;
        this.name = name;
        this.semester = semester;
        this.photourl = photourl;
        this.courses = courses;
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

    public void setName(String name) {
        this.name = name;
    }

    public long getSemester() {
        return semester;
    }

    public void setSemester(long semester) {
        this.semester = semester;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public ArrayList<Long> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Long> courses) {
        this.courses = courses;
    }

    public Student() {}
}
