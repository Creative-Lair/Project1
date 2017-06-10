package com.example.nln.nedroid.Attendance;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nln.nedroid.Helper.Student;
import com.example.nln.nedroid.R;
import com.example.nln.nedroid.Session;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attendance1 extends AppCompatActivity{

    String[] words;
    private RecyclerView recyclerView;
    private ABAdaptor adapter;
    private List<AButton> albumList;
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
        section = session.getSection();
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

        words = subject.split(" ");

        String str = "";

        for(int i=1;i<words.length;i++){
            str += words[i] + " ";
        }


        course.setText(str);
        time.setText(timeslot);
        sec.setText(section);

        albumList = new ArrayList<>();
        adapter = new ABAdaptor(this, albumList);

        mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Student student = dataSnapshot.getValue(Student.class);
                if (student.getCourses() != null) {
                    ArrayList<String> courses = student.getCourses();
                    for (String c : courses) {
                        if (c.equals(words[0]) && student.getSection().equals(section)) {
                            AButton ab = new AButton(dataSnapshot.getKey().substring(1).toUpperCase() + " " + student.getName(), true);
                            albumList.add(ab);
                            adapter.notifyDataSetChanged();
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

    public void onClickButton (View v){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef = database.getReference().child("Classes");


        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c.getTime());

        Lecture lecture = new Lecture(section, words[0], lectureTopic, timeslot, lectureType, lectureImp, formattedDate);

        String id = databaseRef.push().getKey();

        databaseRef = databaseRef.child(words[0]).child(section).push();

        databaseRef.setValue(lecture);

        Map<String,Boolean> attendence = new HashMap<>();

        for(AButton ab: albumList){
            String[] name = ab.getName().split(" ");
            boolean present = ab.isPresent();
            attendence.put(name[0],present);

        }


        databaseRef.child("Attendance").setValue(attendence);

        Toast.makeText(this, "Uploaded", Toast.LENGTH_SHORT).show();

        finish();

    }



}