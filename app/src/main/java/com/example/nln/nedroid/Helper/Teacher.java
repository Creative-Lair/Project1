package com.example.nln.nedroid.Helper;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Teacher {

    private String password;

    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }


    public Teacher(){

    }

    public Teacher(String password, String id) {
        this.password = password;
        Id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
