package com.example.nln.nedroid.NavigationMenu;

/**
 * Created by AHSAN on 6/10/2017.
 */

public class AttendanceList {
    private String code;
    private double classHeld;
    private double classAttend;
    private double percentage;

    public AttendanceList(String code, double classHeld, double classAttend, double percentage) {
        this.code = code;
        this.classHeld = classHeld;
        this.classAttend = classAttend;
        this.percentage = percentage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getClassHeld() {
        return classHeld;
    }

    public void setClassHeld(double classHeld) {
        this.classHeld = classHeld;
    }

    public double getClassAttend() {
        return classAttend;
    }

    public void setClassAttend(double classAttend) {
        this.classAttend = classAttend;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }
}
