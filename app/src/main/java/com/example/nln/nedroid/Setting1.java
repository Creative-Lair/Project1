package com.example.nln.nedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;

import com.example.nln.nedroid.PageOne.FirstNav;

public class Setting1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Switch switchNotice = (Switch) findViewById(R.id.simpleSwitch);
        switchNotice.setText("Notify me when app is updated ");
        Switch switchL = (Switch) findViewById(R.id.switchLogout);
        switchL.setText("Logout ");
        Switch switchR = (Switch) findViewById(R.id.switchRememeber);
        switchR.setText("Remember Settings  ");
    }

}
