package com.example.nln.nedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nln.nedroid.Helper.Teacher;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Nav_AttendTwo extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner_course;
    TextView tvSecA, tvSecB, tvSecC, tvSecD;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference SubRef;
    Teacher teacher;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav__attend_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lecture Count");

        session = new Session(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        firebaseDB =  FirebaseDatabase.getInstance();
        SubRef = firebaseDB.getReference().child("Teachers");

        tvSecA = (TextView) findViewById(R.id.textView_SecA);
        tvSecB = (TextView) findViewById(R.id.textView_SecB);
        tvSecC = (TextView) findViewById(R.id.textView_SecC);
        tvSecD = (TextView) findViewById(R.id.textView_SecD);


        //    teacher = dataSnapshot.getValue(Teacher.class);
        ArrayList<String> subj = teacher.getSubjects();

        spinner_course = (Spinner) findViewById(R.id.spinner_subject);
        spinner_course.setOnItemSelectedListener(this);
        ArrayAdapter<String> CourseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subj);
        CourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(CourseAdapter);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();
        ArrayList<String> subj = teacher.getSubjects();

        if(item.equals(subj.get(0))){
            tvSecA.setText("10");
            tvSecB.setText("20");
            tvSecC.setText("15");
            tvSecD.setText("20");
        }
        else if(item.equals(subj.get(1))){
            tvSecA.setText("20");
            tvSecB.setText("10");
            tvSecC.setText("20");
            tvSecD.setText("15");
        }
        else if(item.equals(subj.get(2))) {
            tvSecA.setText("14");
            tvSecB.setText("20");
            tvSecC.setText("40");
            tvSecD.setText("20");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}