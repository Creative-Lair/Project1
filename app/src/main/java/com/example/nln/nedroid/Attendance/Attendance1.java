package com.example.nln.nedroid.Attendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Nav_AttendOne;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Attendance1 extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ABAdaptor adapter;
    private List<String> albumList;
    private RecyclerView.LayoutManager mLayoutManager;
    private String subject;
    private String section;
    private String timeslot, lectureTopic,lectureType,lectureImp;

    private Session session;

    private TextView course,time,sec;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Attendance");
        session = new Session(this);
        subject = session.getSubject();
        timeslot = session.getTimeslot();
        section = session.getTimeslot();
        lectureImp = session.getLectureImp();
        lectureTopic = session.getLectureTopic();
        lectureType = session.getLectureType();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Students");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView_buttons);
        course = (TextView) findViewById(R.id.textView_coursen);
        time = (TextView) findViewById(R.id.textView_yearn);
        sec = (TextView) findViewById(R.id.textView_sectionn);

        String words[] = subject.split(" ");

        course.setText(words[1]);
        time.setText(timeslot);
        sec.setText(section);

        albumList = new ArrayList<>();
        adapter = new ABAdaptor(this, albumList);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        

    }

    public void onClickButton (View v){
        Toast.makeText(Attendance1.this, "Clicked on Button Upload", Toast.LENGTH_SHORT).show();
    }



}
