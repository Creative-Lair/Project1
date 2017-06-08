package com.example.nln.nedroid.Attendance;

/**
 * Created by AHSAN on 6/8/2017.
 */

public class Lecture_detail {

    String time;
    String date;
    String topic;

    public Lecture_detail(String time, String date, String topic) {

        this.time = time;
        this.date = date;
        this.topic = topic;
    }

    public Lecture_detail() {

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
