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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.Notification.NotificationNav;
import com.example.nln.nedroid.PageOne.Teacher_FirstNav;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Nav_AttendThree extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener  {

    Spinner spinner_section, spinner_subject;
    Button btn;
    private TextView headerName,headerID;

    private ImageView headerIcon;
    private Session session;
    private List<String> course;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference subRef, rootRef;

    private ArrayList<String> subjects;
    private ArrayAdapter<String> SubjectAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_three);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lecture Detail");
        session = new Session(this);

        if (!session.getLogin()) {
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        subjects = session.getCourse();


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
        headerIcon = (ImageView) hView.findViewById(R.id.imageView_nav);

//        (setting username data from login class)
        headerName.setText(session.getUsername());
        headerID.setText(session.getUserId());
        Glide.with(headerIcon.getContext())
                .load(session.getPhoto())
                .into(headerIcon);


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
        course = new ArrayList<String>();

        List<String> section = new ArrayList<String>();
        section.add("A");
        section.add("B");
        section.add("C");
        section.add("D");

        // Creating adapter for spinner
        SubjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, course);
        final ArrayAdapter<String> SectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, section);

        // Drop down layout style - list view with radio button
        SubjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_subject.setAdapter(SubjectAdapter);
        spinner_section.setAdapter(SectionAdapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        subRef = firebaseDatabase.getReference().child("Subjects");

        subRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    for (String sub : subjects) {
                        if (!sub.equals("")) {
                            if (child.getKey().equals(sub)) {
                                String n = child.getKey() + " " + child.getValue();
                                course.add(n);
                            }
                            SubjectAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            startActivity(i);
            finish();

        } if (id == R.id.nav_profile) {
            Intent i = new Intent(Nav_AttendThree.this, Profile.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(Nav_AttendThree.this, Setting1.class);
            startActivity(i);
            finish();

        } else if (id == R.id.nav_notice){
            Intent j = new Intent(this, NotificationNav.class);
            startActivity(j);

        } else if (id == R.id.nav_sync) {
            rootRef = firebaseDatabase.getReference().child("Students");
            rootRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    //Toast.makeText(Login.this, dataSnapshot.getValue(), Toast.LENGTH_SHORT).show();
                    Student student = dataSnapshot.getValue(Student.class);
                    session.setLogin(true);
                    session.setUserId(session.getUserId());
                    session.setUsername(student.getName());
                    session.setSemester(student.getSemester());
                    session.setPhoto(student.getPhotourl());
                    session.setCourses(student.getCourses());
                    session.setUserSemester(student.getSection());
                    Toast.makeText(Nav_AttendThree.this, "Resync Complete", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        } else if (id == R.id.nav_about) {
            Toast.makeText(Nav_AttendThree.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        } else if (id == R.id.nav_logout) {
            session.setLogin(false);
            session.setUserId("");
            session.setUsername("");
            session.setSubject("");
            session.setSection("");
            ArrayList<String> course = new ArrayList<>();
            session.setCourses(course);
            Intent i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void onButtonCreate() {

        String sec = spinner_section.getSelectedItem().toString();
        String[] n = spinner_subject.getSelectedItem().toString().split(" ");
        String code = n[0];

        session.setSubject(code);
        session.setSection(sec);

        //  Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
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
