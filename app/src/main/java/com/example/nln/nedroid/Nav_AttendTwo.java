package com.example.nln.nedroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Nav_AttendTwo extends AppCompatActivity {

    Spinner spinner_course;
    TextView tvSecA, tvSecB, tvSecC, tvSecD;
    int[] counts;
    ArrayAdapter<String> CourseAdapter;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference SubRef,classRef;
    private Session session;
    private ArrayList<String> subj;
    private ArrayList<String> c;

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
        SubRef = firebaseDB.getReference().child("Subjects");

        tvSecA = (TextView) findViewById(R.id.textView_SecA);
        tvSecB = (TextView) findViewById(R.id.textView_SecB);
        tvSecC = (TextView) findViewById(R.id.textView_SecC);
        tvSecD = (TextView) findViewById(R.id.textView_SecD);


        tvSecA.setText("" + 0);
        tvSecB.setText("" + 0);
        tvSecC.setText("" + 0);
        tvSecD.setText("" + 0);

        //    teacher = dataSnapshot.getValue(Teacher.class);
        subj = session.getCourse();

        c= new ArrayList<>();



        spinner_course = (Spinner) findViewById(R.id.spinner_subject);
        CourseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, c);
        CourseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_course.setAdapter(CourseAdapter);
        spinner_course.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String n = parent.getSelectedItem().toString();
                String[] words = n.split(" ");
                final String code = words[0];

                classRef = firebaseDB.getReference().child("Classes");

                tvSecA.setText("" + 0);
                tvSecB.setText("" + 0);
                tvSecC.setText("" + 0);
                tvSecD.setText("" + 0);

                classRef.child(code).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        if (dataSnapshot.getKey().equals("B")) {
                            tvSecB.setText("" + dataSnapshot.getChildrenCount());
                        } else if (dataSnapshot.getKey().equals("C")) {
                            tvSecC.setText("" + dataSnapshot.getChildrenCount());
                        } else if (dataSnapshot.getKey().equals("A")) {
                            tvSecA.setText("" + dataSnapshot.getChildrenCount());
                        } else if (dataSnapshot.getKey().equals("D")) {
                            tvSecD.setText("" + dataSnapshot.getChildrenCount());
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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        SubRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    for (String sub: subj) {
                        if(!sub.equals("")) {
                            if (child.getKey().equals(sub)) {
                                String n = child.getKey() + " " + child.getValue();
                                c.add(n);
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

}