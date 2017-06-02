package com.example.nln.nedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Switch;

import com.example.nln.nedroid.PageOne.FirstNav;

public class Setting1 extends AppCompatActivity {

    public String Name_DB;//for all app
    public String ID_DB;//for all app

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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Setting1.this, FirstNav.class);
        i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
        i.putExtra("USERID", ID_DB);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
