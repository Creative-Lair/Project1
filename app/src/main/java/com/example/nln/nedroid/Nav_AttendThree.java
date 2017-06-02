package com.example.nln.nedroid;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.PageOne.Teacher_FirstNav;

import java.util.ArrayList;
import java.util.List;

public class Nav_AttendThree extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener  {

    Spinner spinner_section, spinner_subject;
    Button btn;
    private TextView headerName,headerID;

    public String Name_DB;//for all app
    public String ID_DB;//for all app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lecture Detail");

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


        btn = (Button) findViewById(R.id.button_create);
        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonCreate();
            }
        });

        // Spinner element
        spinner_subject = (Spinner) findViewById(R.id.spinner_subject);
        spinner_section = (Spinner) findViewById(R.id.spinner_section);

        // Spinner click listener
        spinner_subject.setOnItemSelectedListener(this);
        spinner_section.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> course = new ArrayList<String>();
        course.add("Subject One");
        course.add("Subject Two");
        course.add("Subject Two");

        List<String> section = new ArrayList<String>();
        section.add("Section A");
        section.add("Section B");
        section.add("Section C");
        section.add("Section D");

        // Creating adapter for spinner
        ArrayAdapter<String> SubjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
        ArrayAdapter<String> SectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, section);

        // Drop down layout style - list view with radio button
        SubjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_subject.setAdapter(SubjectAdapter);
        spinner_section.setAdapter(SectionAdapter);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Nav_AttendThree.this, Teacher_FirstNav.class);
        i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(Nav_AttendThree.this, Teacher_FirstNav.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } if (id == R.id.nav_profile) {
            Intent i = new Intent(Nav_AttendThree.this, Profile.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(Nav_AttendThree.this, Setting1.class);
            i.putExtra("USERNAME", Name_DB);// Transfer name from this class to Profile
            i.putExtra("USERID", ID_DB);
            i.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Toast.makeText(Nav_AttendThree.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onButtonCreate() {
        Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, Nav_AttendThree_pdf.class);
        startActivity(i);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
