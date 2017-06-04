package com.example.nln.nedroid.Forum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by NLN on 8/1/2016.
 */
public class Question {

    private String question;
    private String sub;
    private String name;
    private String photourl;
    private String uid;
    private String qid;
    private String subname;
    long timestamp;

    public Question(String question, String sub, String name, String photourl, String uid, String qid, String subname, long timestamp) {
        this.question = question;
        this.sub = sub;
        this.name = name;
        this.photourl = photourl;
        this.uid = uid;
        this.qid = qid;
        this.subname = subname;
        this.timestamp = timestamp;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Question() {
    }

    }