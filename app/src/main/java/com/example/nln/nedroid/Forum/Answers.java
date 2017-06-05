package com.example.nln.nedroid.Forum;

/**
 * Created by AHSAN on 6/4/2017.
 */

public class Answers {

    private String QID;
    private String UID;
    private String answer;
    private String name;
    private String photourl;

    public Answers(String QID, String UID, String answer, String name, String photourl) {
        this.QID = QID;
        this.UID = UID;
        this.answer = answer;
        this.name = name;
        this.photourl = photourl;
    }

    public String getQID() {

        return QID;
    }

    public void setQID(String QID) {
        this.QID = QID;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public Answers(){

    }
}
