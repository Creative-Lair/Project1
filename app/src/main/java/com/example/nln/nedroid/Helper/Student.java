package com.example.nln.nedroid.Helper;

import java.util.ArrayList;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Student {

    private String password;
    private String name;
    private String semester;
    private String photourl;
    private ArrayList<String> courses;
    private String contactno;
    private String discipline;
    private String dob;
    private String domicile;
    private String emailaddress;
    private String fathername;
    private String gender;
    private String martialstatus;
    private String nationality;
    private String permenantaddress;
    private String placeofbirth;
    private String presentaddress;
    private String religion;
    private String year;
    private String section;
    private ArrayList<String> GPA;

    public Student(String password, String name, String semester, String photourl, ArrayList<String> courses, String contactno, String discipline, String dob, String domicile, String emailaddress, String fathername, String gender, String martialstatus, String nationality, String permanentaddress, String placeofbirth, String presentaddress, String religion, String year, String section) {

        this.password = password;
        this.name = name;
        this.semester = semester;
        this.photourl = photourl;
        this.courses = courses;
        this.contactno = contactno;
        this.discipline = discipline;
        this.dob = dob;
        this.domicile = domicile;
        this.emailaddress = emailaddress;
        this.fathername = fathername;
        this.gender = gender;
        this.martialstatus = martialstatus;
        this.nationality = nationality;
        this.permenantaddress = permanentaddress;
        this.placeofbirth = placeofbirth;
        this.presentaddress = presentaddress;
        this.religion = religion;
        this.year = year;
        this.section = section;
    }

    public Student() {
    }

    public ArrayList<String> getGpa() {
        return GPA;
    }

    public void setGpa(ArrayList<String> gpa) {
        this.GPA = gpa;
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getPhotourl() {
        return photourl;
    }

    public void setPhotourl(String photourl) {
        this.photourl = photourl;
    }

    public ArrayList<String> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<String> courses) {
        this.courses = courses;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMartialstatus() {
        return martialstatus;
    }

    public void setMartialstatus(String martialstatus) {
        this.martialstatus = martialstatus;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPermenantaddress() {
        return permenantaddress;
    }

    public void setPermenantaddress(String permanentaddress) {
        this.permenantaddress = permanentaddress;
    }

    public String getPlaceofbirth() {
        return placeofbirth;
    }

    public void setPlaceofbirth(String placeofbirth) {
        this.placeofbirth = placeofbirth;
    }

    public String getPresentaddress() {
        return presentaddress;
    }

    public void setPresentaddress(String presentaddress) {
        this.presentaddress = presentaddress;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}