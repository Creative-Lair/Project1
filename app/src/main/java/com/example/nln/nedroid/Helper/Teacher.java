package com.example.nln.nedroid.Helper;

import java.util.ArrayList;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Teacher {

    private String password;
    private String name;
    private String Id;
    private String photourl;
    private String email;
    private String Department;
    private String JoinDate;
    private String Phone;
    private String Experence;
    private String Designation;
    private ArrayList<String> Subjects;

    public Teacher(){

    }

    public Teacher(String password, String name, String id, String photourl, String email, String department, String joinDate, String phone, String experence, String designation, ArrayList<String> subjects) {
        this.password = password;
        this.name = name;
        Id = id;
        this.photourl = photourl;
        this.email = email;
        Department = department;
        JoinDate = joinDate;
        Phone = phone;
        Experence = experence;
        Designation = designation;
        Subjects = subjects;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(String joinDate) {
        JoinDate = joinDate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getExperence() {
        return Experence;
    }

    public void setExperence(String experence) {
        Experence = experence;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public ArrayList<String> getSubjects() {
        return Subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        Subjects = subjects;
    }
}