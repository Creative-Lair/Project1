package com.example.nln.nedroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.nln.nedroid.PageOne.FirstNav;
import com.github.barteksc.pdfviewer.PDFView;

public class PDF extends AppCompatActivity {

    PDFView pdfView;
    public String Name_DB;//for all app
    public String ID_DB;//for all app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //        PDF for Attendance
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("Attndance.pdf").load();//METHOD 1: PDF from Asset
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(PDF.this, FirstNav.class);
        i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
        i.putExtra("USERID", ID_DB);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}
