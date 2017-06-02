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

public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView headerName,headerID;
    public String Name_DB;//for all app
    public String ID_DB;//for all app
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        setTitle("My Profile");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

//        Navigation Name and id
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);
        headerName = (TextView) hView.findViewById(R.id.textView_nav_name);
        headerID = (TextView) hView.findViewById(R.id.textView_nav_id);

//        (setting username data from login class)
        Name_DB = getIntent().getStringExtra("USERNAME");
        headerName.setText("admin");
        ID_DB = getIntent().getStringExtra("USERID");
        headerID.setText("CS-01");

        String log1 = "Name: " + headerName + " ,ID: " + headerID;
        Log.d("Start: ", log1);

//        PDF for Attendance
        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("Profile.pdf").load();//METHOD 1: PDF from Asset

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, FirstNav.class);
        i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
        i.putExtra("USERID", ID_DB);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(Profile.this, FirstNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } if (id == R.id.nav_profile) {
            Intent i = new Intent(Profile.this, Profile.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_noticeboard) {
            Intent i = new Intent(Profile.this, NotificationNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_attndance) {
            Intent i = new Intent(Profile.this, Attendance1.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(Profile.this, SettingsActivity.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Toast.makeText(Profile.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
