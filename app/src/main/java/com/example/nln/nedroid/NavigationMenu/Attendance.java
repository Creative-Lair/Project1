package com.example.nln.nedroid.NavigationMenu;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Notification.NotificationNav;
import com.example.nln.nedroid.PageOne.FirstNav;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.SettingsActivity;
import com.github.barteksc.pdfviewer.PDFView;

public class Attendance extends AppCompatActivity {

    private TextView headerName,headerID;
    public String Name_DB;//for all app
    public String ID_DB;//for all app
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Attendance");
//        PDF for Attendance
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("Attndance.pdf").load();//METHOD 1: PDF from Asset

    }

}
