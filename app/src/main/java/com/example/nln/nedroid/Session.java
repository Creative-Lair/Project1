package com.example.nln.nedroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setUserId(String userId) {
        editor.putString(KEY_USERID, userId);
        editor.commit();

        Log.d(TAG, "User ID set");
    }

    public boolean getLogin(){
        return pref.getBoolean(KEY_IS_LOGGEDIN,false);
    }

    public String getUserId(){
        return pref.getString(KEY_USERID, null);
    }

    public void setUsername(String username){
        editor.putString(KEY_USERNAME,username);
        editor.commit();


    }

    public String getUsername(){
        return pref.getString(KEY_USERNAME, "Admin");
    }

    public String getPhoto(){
        return pref.getString(KEY_PHOTO, "");
    }

    public void setPhoto(String s){
        editor.putString(KEY_PHOTO,s);
        editor.commit();
    }

    public void setNewsId(String n){
        editor.putString(KEY_NEWS_ID,n);
        editor.commit();

    }

    public String getNewsId(){
        return pref.getString(KEY_NEWS_ID, "-1");
    }

}