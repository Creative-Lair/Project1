package com.example.nln.nedroid.Attendance;

/**
 * Created by NLN on 7/30/2016.
 */
public class AButton {

    private String name;
    private boolean present;
//    protected int count;

    public AButton() {
    }

    public AButton(String name, boolean present) {
        this.name = name;
        this.present = present;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}