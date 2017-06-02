package com.example.nln.nedroid.Forum;

/**
 * Created by NLN on 8/1/2016.
 */
public class Question {

    private String name;
    private int imageProfile;
    private String question;

    public Question() {
    }

    public Question(int imageProfile, String name, String question) {
//    public News(String question, int thumbnail) {
        this.imageProfile = imageProfile;
        this.name = name;
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageProfile() {
        return imageProfile;
    }

}