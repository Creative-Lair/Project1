package com.example.nln.nedroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by AHSAN on 6/2/2017.
 */

public class Session {

    private static final String PREF_NAME = "NEDroid";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final String KEY_USERID = "userid";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PHOTO = "user_photo";
    private static final String KEY_NEWS_ID = "newsid";
    private static final String KEY_SEMESTER = "semester";
    private static final String KEY_SUBJECT_CODE = "subject_code";
    private static final String KEY_SUBJECT_NAME = "subject_name";
    private static final String KEY_QSC = "question_subject_code";
    private static final String KEY_QSN = "question_subject_name";
    private static final String KEY_QID = "question_id";
    private static final String KEY_COURSES = "courses";
    private static final String KEY_COURSES_SIZE = "courses_size";
    private static final String KEY_TIMESLOT = "timeslot";
    private static final String KEY_SECTION = "section";
    private static final String KEY_SUBJECT = "subject";
    private static final String KEY_LECTURE_TOPIC = "lecture_topic";
    private static final String KEY_LECTURE_TYPE = "lecture_type";
    private static final String KEY_LECTURE_IMP = "lecture_imp";
    private static final String KEY_USER_SEMESTER = "user_semester";



    private static String TAG = Session.class.getSimpleName();
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;


    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean getLogin() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public String getUserId() {
        return pref.getString(KEY_USERID, null);
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USERID, userId);
        editor.commit();

        Log.d(TAG, "User ID set");
    }

    public String getUsername() {
        return pref.getString(KEY_USERNAME, "Admin");
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME,username);
        editor.commit();


    }

    public String getPhoto(){
        return pref.getString(KEY_PHOTO, "");
    }

    public void setPhoto(String s){
        editor.putString(KEY_PHOTO,s);
        editor.commit();
    }

    public String getNewsId() {
        return pref.getString(KEY_NEWS_ID, "-1");
    }

    public void setNewsId(String n){
        editor.putString(KEY_NEWS_ID,n);
        editor.commit();

    }

    public String getSemester() {
        return pref.getString(KEY_SEMESTER, "-1");
    }

    public void setSemester(String s){
        editor.putString(KEY_SEMESTER, s);
        editor.commit();
    }

    public String getSubjectCode() {
        return pref.getString(KEY_SUBJECT_CODE, "0000");
    }

    public void setSubjectCode(String n){
        editor.putString(KEY_SUBJECT_CODE, n);
        editor.commit();
    }

    public String getSubjectName(){
        return pref.getString(KEY_SUBJECT_NAME, "subject name");
    }

    public void setSubjectName(String name){
        editor.putString(KEY_SUBJECT_NAME, name);
        editor.commit();
    }

    public String getQSC() {
        return pref.getString(KEY_QSC, "0000");
    }

    public void setQSC(String n){
        editor.putString(KEY_QSC, n);
        editor.commit();
    }

    public String getQSN() {
        return pref.getString(KEY_QSN, "0000");
    }

    public void setQSN(String n){
        editor.putString(KEY_QSN, n);
        editor.commit();

    }

    public String getQID() {
        return pref.getString(KEY_QID, "-1");
    }

    public void setQID(String n){
        editor.putString(KEY_QID,n);
        editor.commit();
    }

    public void setCourses(ArrayList<String> courses){

        editor.putInt(KEY_COURSES_SIZE,courses.size());
        for(int i=0;i<courses.size();i++){
            editor.putString(KEY_COURSES + i, courses.get(i));
        }
        editor.commit();
    }

    public ArrayList<String> getCourse(){
        ArrayList<String> courses = new ArrayList<>();

        for(int i=0;i<pref.getInt(KEY_COURSES_SIZE,0);i++){
            courses.add(pref.getString(KEY_COURSES+i,"-1"));
        }

        return courses;
    }

    public String getSubject() {
        return pref.getString(KEY_SUBJECT, "CS000");
    }

    public void setSubject(String n){
        editor.putString(KEY_SUBJECT, n);
        editor.commit();
    }

    public String getTimeslot() {
        return pref.getString(KEY_TIMESLOT, "");
    }

    public void setTimeslot(String n){
        editor.putString(KEY_TIMESLOT, n);
        editor.commit();
    }

    public String getSection() {
        return pref.getString(KEY_SECTION, "A");
    }

    public void setSection(String n){
        editor.putString(KEY_SECTION , n);
        editor.commit();
    }

    public String getLectureTopic() {
        return pref.getString(KEY_LECTURE_TOPIC, "");
    }

    public void setLectureTopic(String n){
        editor.putString(KEY_LECTURE_TOPIC,n);
        editor.commit();
    }

    public String getLectureType() {
        return pref.getString(KEY_LECTURE_TYPE, "");
    }

    public void setLectureType(String n){
        editor.putString(KEY_LECTURE_TYPE,n);
        editor.commit();
    }

    public String getLectureImp() {
        return pref.getString(KEY_LECTURE_IMP, "");

    }

    public void setLectureImp(String n){
        editor.putString(KEY_LECTURE_IMP, n);
        editor.commit();
    }

    public String getUserSemester() {
        return pref.getString(KEY_USER_SEMESTER, "-1");
    }

    public void setUserSemester(String n) {
        editor.putString(KEY_USER_SEMESTER, n);
        editor.commit();
    }

}