package com.example.nln.nedroid.Attendance;

/**
 * Created by AHSAN on 6/8/2017.
 */

public class Lecture {

    private String section;
    private String subject;
    private String lectureTopic;
    private String timeslot;
    private String lectureType;
    private String lectureImp;
    private String date;

    public Lecture(String section, String subject, String lectureTopic, String timeslot, String lectureType, String lectureImp, String date) {
        this.section = section;
        this.subject = subject;
        this.lectureTopic = lectureTopic;
        this.timeslot = timeslot;
        this.lectureType = lectureType;
        this.lectureImp = lectureImp;
        this.date = date;
    }

    public Lecture() {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLectureTopic() {
        return lectureTopic;
    }

    public void setLectureTopic(String lectureTopic) {
        this.lectureTopic = lectureTopic;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }

    public String getLectureType() {
        return lectureType;
    }

    public void setLectureType(String lectureType) {
        this.lectureType = lectureType;
    }

    public String getLectureImp() {
        return lectureImp;
    }

    public void setLectureImp(String lectureImp) {
        this.lectureImp = lectureImp;
    }
}