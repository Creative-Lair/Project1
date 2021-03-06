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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.nln.nedroid.Attendance.Attendance1;
import com.example.nln.nedroid.Helper.Teacher;
import com.example.nln.nedroid.Notification.NotificationNav;
import com.example.nln.nedroid.PageOne.Teacher_FirstNav;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class Nav_AttendOne extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,AdapterView.OnItemSelectedListener {

    Spinner spinner_course, spinner_section, spinner_timetable;
    Button btn;
    RadioGroup RadioGroupOne,RadioGroupTwo;
    RadioButton Regular, Compensatory, Theory, Practical;
    String buttonSelected, buttonSelected1;
    ArrayAdapter<String> SectionAdapter;
    ArrayAdapter<String> TimeSlotAdapter;
    private TextView headerName,headerID;
    private ImageView headerIcon;
    private EditText editText;
    private Session session;
    private ArrayList<String> courses;
    private ArrayList<String> course;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference subjectRef, rootRef;
    private ArrayAdapter<String> CourseAdapter;
    private String lectureType, lectureImp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_one);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        setTitle("Attendance");
        session = new Session(this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        subjectRef = firebaseDatabase.getReference().child("Subjects");
        courses = session.getCourse();
        course = new ArrayList<>();

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
        editText = (EditText) findViewById(R.id.lecture);

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onButtonCreate();
            }
        });

        // Radio Button & Groups
        RadioGroupOne = (RadioGroup) findViewById(R.id.RadioGroupOne);
        RadioGroupTwo = (RadioGroup) findViewById(R.id.RadioGroupTwo);
        Regular = (RadioButton) findViewById(R.id.Regular);
        Compensatory = (RadioButton) findViewById(R.id.Compensatory);
        Theory = (RadioButton) findViewById(R.id.Theory);
        Practical = (RadioButton) findViewById(R.id.Practical);

        RadioGroupOne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.Regular:
                        buttonSelected = "Regular Selected";
                        lectureImp = "Regular";
                        Compensatory.setChecked(false);
                        break;
                    case R.id.Compensatory:
                        buttonSelected = "Compensatory Selected";
                        lectureImp = "Compensatory";
                        Regular.setChecked(false);
                        break;
                    default:

                }
                Toast.makeText(Nav_AttendOne.this, "Selected.. " + buttonSelected, Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroupTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.Theory:
                        buttonSelected1 = "Theory Selected";
                        lectureType = "Theory";
                        Practical.setChecked(false);
                        break;
                    case R.id.Practical:
                        buttonSelected1 = "Practical Selected";
                        lectureType = "Practical";
                        Theory.setChecked(false);
                        break;
                    default:

                }
                Toast.makeText(Nav_AttendOne.this, "Selected.. " + buttonSelected1, Toast.LENGTH_SHORT).show();
            }
        });

        // Spinner element
        spinner_course = (Spinner) findViewById(R.id.spinner_course);
        spinner_section = (Spinner) findViewById(R.id.spinner_section);
        spinner_timetable = (Spinner) findViewById(R.id.spinner_timeslot);

        // Spinner click listener
        spinner_course.setOnItemSelectedListener(this);
        spinner_section.setOnItemSelectedListener(this);
        spinner_timetable.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> section = new ArrayList<String>();
        section.add("A");
        section.add("B");
        section.add("C");
        section.add("D");

        List<String> timetable = new ArrayList<String>();
        timetable.add("08:30-09:20 am");
        timetable.add("09:20-10:10 am");
        timetable.add("10:10-11:00 am");
        timetable.add("11:30-12:20 pm");
        timetable.add("12:20-13:10 pm");
        timetable.add("14:00-14:50 pm");
        timetable.add("14:50-15:40 pm");
        timetable.add("15:40-16:30 pm");


        // Creating adapter for spinner
        CourseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, course);
        SectionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, section);
        TimeSlotAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timetable);

        // Drop down layout style - list view with radio button
        CourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TimeSlotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_course.setAdapter(CourseAdapter);
        spinner_section.setAdapter(SectionAdapter);
        spinner_timetable.setAdapter(TimeSlotAdapter);


        subjectRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    for (String sub: courses) {
                        if(!sub.equals("")) {
                            if (child.getKey().equals(sub)) {
                                String n = child.getKey() + " " + child.getValue();
                                course.add(n);
                            }
                            CourseAdapter.notifyDataSetChanged();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(Nav_AttendOne.this, Teacher_FirstNav.class);
            startActivity(i);

        } if (id == R.id.nav_profile) {
            Intent i = new Intent(Nav_AttendOne.this, Profile.class);
            startActivity(i);

        } else if (id == R.id.nav_setting) {
            Intent i = new Intent(Nav_AttendOne.this, Setting1.class);
            startActivity(i);

        } else if (id == R.id.nav_notice){
            Intent j = new Intent(this, NotificationNav.class);
            startActivity(j);

        } else if (id == R.id.nav_sync) {
            rootRef = firebaseDatabase.getReference().child("Teachers");
            rootRef.child(session.getUserId()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Teacher teacher = dataSnapshot.getValue(Teacher.class);
                    session.setLogin(true);
                    session.setUserId(session.getUserId());
                    session.setUsername(teacher.getName());
                    session.setPhoto(teacher.getPhotourl());
                    session.setCourses(teacher.getSubjects());
                    ArrayList<String> subcriptions = teacher.getSubjects();
                    for (String str : subcriptions) {
                        System.out.println(str);
                        FirebaseMessaging.getInstance().subscribeToTopic(str + "q");
                    }

                    FirebaseMessaging.getInstance().subscribeToTopic("Notice");
                    Toast.makeText(Nav_AttendOne.this, "Resync Complete", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        else if (id == R.id.nav_about) {
            Toast.makeText(Nav_AttendOne.this, " Link to webite ", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse("http://www.android.com");
            Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(webIntent);
        } else if (id == R.id.nav_logout) {
            session.setLogin(false);
            session.setUserId("");
            session.setUsername("");
            session.setSubject("");
            session.setSection("");
            ArrayList<String> subcriptions = session.getCourse();
            for (String str : subcriptions) {
                System.out.println(str);
                FirebaseMessaging.getInstance().unsubscribeFromTopic(str + "c");
                FirebaseMessaging.getInstance().unsubscribeFromTopic(str + "q");
            }
            FirebaseMessaging.getInstance().unsubscribeFromTopic("News");
            FirebaseMessaging.getInstance().unsubscribeFromTopic("Notice");

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

        session.setLectureImp(lectureImp);
        session.setLectureType(lectureType);
        session.setLectureTopic(editText.getText().toString().trim());
        session.setSection(spinner_section.getSelectedItem().toString());
        session.setTimeslot(spinner_timetable.getSelectedItem().toString());
        session.setSubject(spinner_course.getSelectedItem().toString());

        Intent i = new Intent(this, Attendance1.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
