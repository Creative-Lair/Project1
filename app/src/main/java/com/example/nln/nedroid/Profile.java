package com.example.nln.nedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.nln.nedroid.PageOne.Teacher_FirstNav;
import com.github.barteksc.pdfviewer.PDFView;

public class Profile extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //        PDF for Attendance
//        pdfView = (PDFView) findViewById(R.id.pdfView);
//        pdfView.fromAsset("Profile.pdf").load();//METHOD 1: PDF from Asset
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, Teacher_FirstNav.class);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
